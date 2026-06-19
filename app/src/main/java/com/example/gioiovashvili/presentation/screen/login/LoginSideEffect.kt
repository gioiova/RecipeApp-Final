package com.example.gioiovashvili.presentation.screen.login

sealed interface LoginSideEffect {
    object NavigateToHome : LoginSideEffect
    data class ShowToast(val theMessage: String) : LoginSideEffect
}