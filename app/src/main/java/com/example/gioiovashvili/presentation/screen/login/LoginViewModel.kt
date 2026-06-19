package com.example.gioiovashvili.presentation.screen.login

import com.example.gioiovashvili.presentation.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
) : BaseViewModel<LoginState, LoginSideEffect>(LoginState()) {
    fun onEmailChanged(newEmail: String) {
        updateState { it.copy(email = newEmail, error = null) }
    }

    fun onPasswordChanged(newPassword: String) {
        updateState { it.copy(password = newPassword, error = null) }
    }

    fun onLoginClick() {
        val currentEmail = state.value.email
        val currentPassword = state.value.password

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(currentEmail).matches()) {
            updateState { it.copy(error = "Enter correct email!") }
            return
        }

        viewModelScope.launch {
            updateState { it.copy(isLoading = true, error = null) }

            try {
                delay(2000)

                if (currentEmail == "gioiova@gmail.com" && currentPassword == "11111111") {
                    emitSideEffect(LoginSideEffect.NavigateToHome)
                } else {
                    updateState { it.copy(error = "incorrect email or password", isLoading = false) }
                }

            } catch (e: Exception) {
                updateState { it.copy(isLoading = false) }
                emitSideEffect(LoginSideEffect.ShowToast("network error: ${e.localizedMessage}"))
            }
        }
    }
}