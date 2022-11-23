package com.example.waridihomes.data.api

import com.example.waridihomes.data.model.modelrequest.LoginRequest
import com.example.waridihomes.data.model.modelrequest.UserRequest
import com.example.waridihomes.data.model.modelresponse.LoginResponse
import com.example.waridihomes.data.model.modelresponse.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface WaridiHomesApiService {

    //Register User
    @POST("/auth/signup/")
    suspend fun registerUser(@Body user: UserRequest): Response<UserResponse>

    //Login user
    @POST("/auth/login2/")
    suspend fun loginUser(@Body login: LoginRequest): Response<LoginResponse>
}