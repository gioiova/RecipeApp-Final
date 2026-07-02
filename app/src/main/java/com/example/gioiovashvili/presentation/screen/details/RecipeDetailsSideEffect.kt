package com.example.gioiovashvili.presentation.screen.details

sealed interface RecipeDetailsSideEffect {
    data object NavigateBack : RecipeDetailsSideEffect
}