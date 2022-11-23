package com.example.waridihomes.data.repository.datasource

import com.example.waridihomes.data.model.modelrequest.UserRequest
import com.example.waridihomes.data.model.modelresponse.UserResponse
import retrofit2.Response

interface WaridiHomesRemoteDataSource {

    suspend fun registerUser(user: UserRequest): Response<UserResponse>
}