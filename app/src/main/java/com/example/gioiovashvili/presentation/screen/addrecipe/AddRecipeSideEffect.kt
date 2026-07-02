package com.example.gioiovashvili.presentation.screen.addrecipe

sealed interface AddRecipeSideEffect {
    data class ShowToast(val message: String) : AddRecipeSideEffect
    data object NavigateBack : AddRecipeSideEffect
}
