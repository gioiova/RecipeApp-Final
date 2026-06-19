package com.example.gioiovashvili.presentation.screen.home

sealed interface HomeSideEffect {
    data class ShowToast(val message: String) : HomeSideEffect
}