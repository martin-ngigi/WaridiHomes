package com.example.waridihomes.domain.usecase

import android.util.Log
import com.example.waridihomes.data.model.modelrequest.LoginRequest
import com.example.waridihomes.data.model.modelrequest.UserRequest
import com.example.waridihomes.data.model.modelresponse.LoginResponse
import com.example.waridihomes.data.model.modelresponse.UserResponse
import com.example.waridihomes.data.util.Constants
import com.example.waridihomes.data.util.Resource
import com.example.waridihomes.domain.repository.WaridiHomesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AuthUseCase @Inject constructor(
    private val waridiHomesRepository: WaridiHomesRepository
) {
    private val TAG = "AuthUseCase"

    fun registerUser(userRequest: UserRequest): Flow<Resource<UserResponse>> = flow {
        emit(Resource.Loading())
        //create a demo user and upload
        try {
            val response = waridiHomesRepository.registerUser(user = userRequest)
            emit(response)
        }
        catch (e: HttpException){
            Log.e(TAG, "AuthUseCase registerUser Error:${ e.localizedMessage!!}")
            emit(Resource.Error(e.localizedMessage?: "HttpException Error has occurred\nHint: registerUser Error:${ e.localizedMessage!!}"))
        }
        catch (e: IOException){
            Log.e(TAG, "AuthUseCase registerUser Error:${ e.localizedMessage!!}")
            emit(Resource.Error(e.localizedMessage?: "Couldn't reach server. Check your internet connection.\nHint: registerUser Error:${ e.localizedMessage!!}"))
        }
    }

    fun loginUser(loginRequest: LoginRequest): Flow<Resource<LoginResponse>> = flow {
        emit(Resource.Loading())
        try {
            val response = waridiHomesRepository.loginUser(login = loginRequest)
//            Log.i(TAG, "loginUser successful \nAccess Token: ${response.data?.access} \n Refresh Token:  ${response.data?.refresh} \nWHOLE RESPONSE:\n${response.data?.user?.firstName.toString()}")
            //save data in constants
            Constants.ID = response.data?.user?.id
            Constants.FNAME = response.data?.user?.firstName.toString()
            Constants.LNAME = response.data?.user?.lastLogin.toString()
            Constants.EMAIL = response.data?.user?.email.toString()
            Constants.PHONE = response.data?.user?.phone.toString()
            Constants.ACCESS_TOKEN = response.data?.access.toString()
            Constants.REFRESH_TOKEN = response.data?.refresh.toString()

            Log.i(TAG, "Getting data from API was successful. \nAccess Token: ${response.data?.access} \n Refresh Token:  ${response.data?.refresh} \nFirstName:\n${response.data?.user?.firstName.toString()}" +
                    " \n" +
                    "LastName:\n" +
                    "${response.data?.user?.lastName.toString()}")

        }
        catch (e: HttpException){
            Log.e(TAG, "AuthUseCase loginUser Error:${e.localizedMessage!!}")
            emit(Resource.Error(e.localizedMessage?: "HttpException error. An expected has occurred ${e.toString()}"))
        }
        catch (e: IOException){
            Log.e(TAG, "AuthUseCase loginUser Error:${e.localizedMessage!!}")
            emit(Resource.Error(e.localizedMessage?: "Couldn't reach server. Check your internet connection.\nHint: registerUser Error:${ e.localizedMessage!!}"))
        }
    }
}