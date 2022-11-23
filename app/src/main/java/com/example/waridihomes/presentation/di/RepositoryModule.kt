package com.example.waridihomes.presentation.di

import com.example.waridihomes.data.repository.WaridiHomesRepositoryImpl
import com.example.waridihomes.data.repository.datasource.WaridiHomesRemoteDataSource
import com.example.waridihomes.domain.repository.WaridiHomesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun providesWaridiHomesRepository(
        waridiHomesRemoteDataSource: WaridiHomesRemoteDataSource
    ): WaridiHomesRepository{
        return WaridiHomesRepositoryImpl(
            waridiHomesRemoteDataSource = waridiHomesRemoteDataSource
        )
    }
}