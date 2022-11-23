package com.example.waridihomes.domain.repository

import com.example.waridihomes.data.model.modelrequest.UserRequest
import com.example.waridihomes.data.model.modelresponse.UserResponse
import com.example.waridihomes.data.util.Resource
import retrofit2.Response

interface WaridiHomesRepository {

    suspend fun registerUser(user: UserRequest) : Resource<UserResponse>
}