package com.example.waridihomes.presentation.di

import com.example.waridihomes.data.util.SharedPreference
import com.example.waridihomes.presentation.viewmodel.SplashViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ViewModelModule {
    @Provides
    @Singleton
    fun providesSplashViewModel(sharedPreference: SharedPreference): SplashViewModel{
       return SplashViewModel((sharedPreference))
    }
}