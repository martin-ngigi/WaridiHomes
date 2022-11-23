package com.example.waridihomes.data.repository

import com.example.waridihomes.data.model.modelrequest.UserRequest
import com.example.waridihomes.data.model.modelresponse.UserResponse
import com.example.waridihomes.data.repository.datasource.WaridiHomesRemoteDataSource
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


    override suspend fun registerUser(user: UserRequest): Resource<UserResponse> {
        return responseToUserResult(waridiHomesRemoteDataSource.registerUser(user = user))
    }
}