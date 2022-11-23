package com.example.waridihomes.domain

import android.util.Log
import com.example.waridihomes.data.model.modelrequest.UserRequest
import com.example.waridihomes.data.model.modelresponse.UserResponse
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
            Log.i(TAG, "AuthUseCase registerUser Error:${ e.localizedMessage!!}")
            emit(Resource.Error(e.localizedMessage?: "HttpException Error has occurred\nHint: registerUser Error:${ e.localizedMessage!!}"))
        }
        catch (e: IOException){
            Log.i(TAG, "AuthUseCase registerUser Error:${ e.localizedMessage!!}")
            emit(Resource.Error(e.localizedMessage?: "Couldn't reach server. Check your internet connection.\nHint: registerUser Error:${ e.localizedMessage!!}"))
        }
    }
}