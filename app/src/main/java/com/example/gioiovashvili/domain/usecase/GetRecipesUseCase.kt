package com.example.gioiovashvili.domain.usecase

import com.example.gioiovashvili.domain.repository.RecipesRepository
import javax.inject.Inject

class GetRecipesUseCase @Inject constructor(private val repository: RecipesRepository) {
    suspend operator fun invoke() = repository.getRecipes()
}