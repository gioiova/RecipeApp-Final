package com.example.gioiovashvili.domain.repository

import com.example.gioiovashvili.domain.common.Resource
import com.example.gioiovashvili.domain.model.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipesRepository {
    suspend fun getRecipes(): Flow<Resource<List<Recipe>>>
    suspend fun addRecipe(recipe: Recipe)
    suspend fun getRecipeById(id: String): Recipe?
}