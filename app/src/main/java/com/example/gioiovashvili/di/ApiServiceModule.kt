package com.example.gioiovashvili.di

import com.example.gioiovashvili.data.apiService.GetRecipesApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiServiceModule {
    @Provides
    @Singleton
    fun provideGetRecipesApiService(retrofit: Retrofit): GetRecipesApiService {
        return retrofit.create(GetRecipesApiService::class.java)
    }
}