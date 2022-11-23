package com.example.waridihomes.data.repository.datasourceImp

import com.example.waridihomes.data.api.WaridiHomesApiService
import com.example.waridihomes.data.model.modelrequest.LoginRequest
import com.example.waridihomes.data.model.modelrequest.UserRequest
import com.example.waridihomes.data.model.modelresponse.LoginResponse
import com.example.waridihomes.data.model.modelresponse.UserResponse
import com.example.waridihomes.data.repository.datasource.WaridiHomesRemoteDataSource
import retrofit2.Response
import javax.inject.Inject

class WaridiHomesRemoteDataSourceImpl @Inject constructor(
    private val waridiHomesApiService: WaridiHomesApiService
): WaridiHomesRemoteDataSource {
    override suspend fun registerUser(user: UserRequest): Response<UserResponse> {
        return waridiHomesApiService.registerUser(user = user)
    }

    override suspend fun loginUser(login: LoginRequest): Response<LoginResponse> {
        return  waridiHomesApiService.loginUser(login = login)
    }
}