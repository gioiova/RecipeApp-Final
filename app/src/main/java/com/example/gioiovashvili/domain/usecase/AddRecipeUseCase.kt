package com.example.gioiovashvili.domain.usecase

import com.example.gioiovashvili.domain.model.Recipe
import com.example.gioiovashvili.domain.repository.RecipesRepository
import javax.inject.Inject

class AddRecipeUseCase @Inject constructor(
    private val repository: RecipesRepository
) {
    suspend operator fun invoke(recipe: Recipe) = repository.addRecipe(recipe)
}
