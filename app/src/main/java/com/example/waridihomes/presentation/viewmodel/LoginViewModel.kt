package com.example.waridihomes.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.waridihomes.data.model.modelrequest.LoginRequest
import com.example.waridihomes.data.util.Resource
import com.example.waridihomes.data.util.SharedPreference
import com.example.waridihomes.domain.usecase.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
    private val sharedPrefUtil: SharedPreference
): ViewModel(){

    val TAG = "LoginViewModel"
    val  successful: MutableLiveData<Boolean?> = MutableLiveData()
    val error: MutableLiveData<String?> = MutableLiveData()

    val loggedIn: String = sharedPrefUtil.getUserToken()

    private fun saveUserAccessToken(token: String) = sharedPrefUtil.saveUserAccessToken(token)

    fun login(loginRequest: LoginRequest){
        authUseCase.loginUser(loginRequest = loginRequest).onEach { result->
            when(result){
                is Resource.Loading -> {
                    Log.i(TAG, "login: Logging is loading, please wait ")
                }
                is Resource.Error ->{
                    error.postValue("${result.message}")
                    successful.postValue(false)
                    Log.i(TAG, "login error: logging error has occurred. : ${result.message}")
                }
                is  Resource.Success ->{
                    successful.postValue(true)
                    saveUserAccessToken("${result.data?.access}")
                    Log.i(TAG, "login successful: You have successfully logged in. Your id is ${result.data?.user?.id} ")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun navigateToPage(){
        successful.postValue(null)
        error.postValue(null)
    }
}