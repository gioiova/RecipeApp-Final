package com.example.gioiovashvili.data.repository

import com.example.gioiovashvili.data.apiService.GetRecipesApiService
import com.example.gioiovashvili.data.common.HandleResponse
import com.example.gioiovashvili.data.common.asResource
import com.example.gioiovashvili.domain.common.Resource
import com.example.gioiovashvili.domain.model.Recipe
import com.example.gioiovashvili.domain.repository.RecipesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RecipesRepositoryImpl @Inject constructor (
    private val apiService: GetRecipesApiService,
    private val handleResponse: HandleResponse
) : RecipesRepository {

    override suspend fun getRecipes(): Flow<Resource<List<Recipe>>> {
        return handleResponse.safeApiCall { apiService.getFields() }
            .asResource { responseDto ->
                responseDto.recipes.map { recipeDto ->
                    Recipe(
                        id = recipeDto.id,
                        title = recipeDto.title,
                        description = recipeDto.description,
                        imageUrl = recipeDto.imageUrl,
                        totalTimeMinutes = recipeDto.prepTimeMinutes + recipeDto.cookTimeMinutes,
                        difficulty = recipeDto.difficulty,
                        rating = recipeDto.rating,
                        ingredients = recipeDto.ingredients,
                        instructions = recipeDto.instructions
                    )
                }
            }
    }
}