package com.example.gioiovashvili.di

import com.example.gioiovashvili.data.repository.RecipesRepositoryImpl
import com.example.gioiovashvili.domain.repository.RecipesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindRecipeRepository(
        recipeRepositoryImpl: RecipesRepositoryImpl
    ): RecipesRepository
}