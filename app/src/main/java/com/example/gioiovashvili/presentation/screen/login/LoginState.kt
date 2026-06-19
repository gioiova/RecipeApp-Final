package com.example.gioiovashvili.presentation.screen.login

data class LoginState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)