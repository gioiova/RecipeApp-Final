package com.example.gioiovashvili.presentation.screen.details

import com.example.gioiovashvili.domain.model.Recipe

data class RecipeDetailsState(
    val isLoading: Boolean = false,
    val recipe: Recipe? = null,
    val error: String? = null
)