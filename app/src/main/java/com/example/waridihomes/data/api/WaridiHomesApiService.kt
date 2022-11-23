package com.example.waridihomes.data.api

import com.example.waridihomes.data.model.modelrequest.UserRequest
import com.example.waridihomes.data.model.modelresponse.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface WaridiHomesApiService {

    //Register User
    @POST("/auth/signup/")
    suspend fun registerUser(@Body user: UserRequest): Response<UserResponse>
}