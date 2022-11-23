package com.example.waridihomes.presentation.di

import com.example.waridihomes.domain.usecase.AuthUseCase
import com.example.waridihomes.domain.repository.WaridiHomesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    @Singleton
    fun providesAuthUseCase(waridiHomesRepository: WaridiHomesRepository): AuthUseCase {
        return AuthUseCase(waridiHomesRepository = waridiHomesRepository)
    }
}