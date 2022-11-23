package com.example.waridihomes.data.repository

import com.example.waridihomes.data.model.modelrequest.LoginRequest
import com.example.waridihomes.data.model.modelrequest.UserRequest
import com.example.waridihomes.data.model.modelresponse.LoginResponse
import com.example.waridihomes.data.model.modelresponse.UserResponse
import com.example.waridihomes.data.repository.datasource.WaridiHomesRemoteDataSource
import com.example.waridihomes.data.util.Constants
import com.example.waridihomes.data.util.Resource
import com.example.waridihomes.domain.repository.WaridiHomesRepository
import retrofit2.Response
import javax.inject.Inject

class WaridiHomesRepositoryImpl @Inject constructor(
    private val waridiHomesRemoteDataSource: WaridiHomesRemoteDataSource
): WaridiHomesRepository {


    private fun responseToUserResult(response: Response<UserResponse>) : Resource<UserResponse>{
        if (response.isSuccessful){
            response.body()?.let { result ->
                return  Resource.Success(result)
            }
        }
        //else
        return Resource.Error(message = "1.User Error: ${response.errorBody()?.toString()}")
    }

    private fun responseToString(response: Response<LoginResponse>) : Resource<LoginResponse>{
        if (response.isSuccessful){
            response.body()?.let {
                return Resource.Success(it)
                //save login details to Constants
                Constants.LOGIN_RESPONSE = response.body().toString()
            }
        }
        //else
        return Resource.Error(message = "1, Login Error: ${response.errorBody()?.toString()}" )
    }


    override suspend fun registerUser(user: UserRequest): Resource<UserResponse> {
        return responseToUserResult(waridiHomesRemoteDataSource.registerUser(user = user))
    }

//    override suspend fun loginUser(login: LoginRequest): Resource<LoginResponse>{
//        return responseToString(waridiHomesRemoteDataSource.loginUser(login = login))
//    }

    override suspend fun loginUser(login: LoginRequest): Resource<LoginResponse> {
        return responseToString(waridiHomesRemoteDataSource.loginUser(login = login))
    }

}