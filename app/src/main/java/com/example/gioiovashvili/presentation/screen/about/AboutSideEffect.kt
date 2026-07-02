package com.example.gioiovashvili.presentation.screen.about

sealed interface AboutSideEffect {
    data object NavigateBack : AboutSideEffect
}
