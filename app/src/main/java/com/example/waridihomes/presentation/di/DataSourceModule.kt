package com.example.waridihomes.presentation.di

import com.example.waridihomes.data.api.WaridiHomesApiService
import com.example.waridihomes.data.repository.datasource.WaridiHomesRemoteDataSource
import com.example.waridihomes.data.repository.datasourceImp.WaridiHomesRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Singleton
    @Provides
    fun provideWaridiHomesDataSource(waridiHomesApiService: WaridiHomesApiService): WaridiHomesRemoteDataSource{
        return WaridiHomesRemoteDataSourceImpl(waridiHomesApiService = waridiHomesApiService)
    }
}