package com.example.waridihomes.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.waridihomes.data.model.modelrequest.UserRequest
import com.example.waridihomes.domain.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import com.example.waridihomes.data.util.Resource
import kotlinx.coroutines.flow.launchIn

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
) : ViewModel(){
    
    val TAG = "RegisterViewModel"
    val successful: MutableLiveData<Boolean?> = MutableLiveData()
    val error: MutableLiveData<String?> = MutableLiveData()
    
    fun registerUser(userRequest: UserRequest){
        authUseCase.registerUser(userRequest = userRequest).onEach { result ->
            when(result){
                is Resource.Loading ->{
                    Log.i(TAG, "registerUser: Loading. Please wait...")
                }
                is Resource.Error ->{
                    error.postValue("registerUser>>> Error: ${result.message}")
                    successful.postValue(false)
                    Log.i(TAG, "registerUser>>> Error: ${result.message} ")
                }
                is Resource.Success -> {
                    successful.postValue(true)
                    Log.i(TAG, "registerUser: Successfully registered user: ${result.data}")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun navigateToPage(){
        successful.postValue(null)
        error.postValue(null)
    }
}