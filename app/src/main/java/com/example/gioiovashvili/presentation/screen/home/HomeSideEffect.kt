package com.example.gioiovashvili.presentation.screen.home

sealed interface HomeSideEffect {
    data class ShowToast(val message: String) : HomeSideEffect
    data object NavigateToLogin : HomeSideEffect
    data object NavigateToAbout : HomeSideEffect
    data object NavigateToAddRecipe : HomeSideEffect
}