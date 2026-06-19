package com.example.gioiovashvili.presentation.screen.login

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.gioiovashvili.R
import com.example.gioiovashvili.presentation.extension.CollectSideEffects

@Composable
fun LoginScreen(
    onNavigateToHome: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val state by viewModel.state.collectAsStateWithLifecycle()

    viewModel.sideEffect.CollectSideEffects { sideEffect ->
        when (sideEffect) {
            is LoginSideEffect.NavigateToHome -> onNavigateToHome()

            is LoginSideEffect.ShowToast -> {
                Toast.makeText(context, sideEffect.theMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }

    LoginContent(
        state = state,
        onEmailChange = { viewModel.onEmailChanged(it) },
        onPasswordChange = { viewModel.onPasswordChanged(it) },
        onLoginClick = { viewModel.onLoginClick() }
    )
}

@Composable
private fun LoginContent(
    state: LoginState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.yumrecipe),
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(R.string.welcome_text),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = state.email,
            onValueChange = onEmailChange,
            label = { Text(stringResource(R.string.email)) },
            leadingIcon = { Icon(Icons.Default.Email, contentDescription = "Email") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = state.password,
            onValueChange = onPasswordChange,
            label = { Text(stringResource(R.string.password)) },
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        if (state.error != null) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = state.error,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.align(Alignment.Start)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        if (state.isLoading) {
            CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
        } else {
            Button(
                onClick = onLoginClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                enabled = state.email.isNotBlank() && state.password.isNotBlank()
            ) {
                Text(stringResource(R.string.log_in), style = MaterialTheme.typography.titleMedium)
            }
        }
    }
}