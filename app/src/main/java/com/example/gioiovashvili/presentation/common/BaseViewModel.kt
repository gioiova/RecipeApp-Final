package com.example.gioiovashvili.presentation.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<State, SideEffect>(
    initialState: State
) : ViewModel() {

    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<State> = _state.asStateFlow()

    private val _sideEffect = Channel<SideEffect>(Channel.BUFFERED)
    val sideEffect: Flow<SideEffect> = _sideEffect.receiveAsFlow()

    protected fun updateState(update: (State) -> State) {
        _state.update(update)
    }

    protected fun emitSideEffect(sideEffect: SideEffect) {
        viewModelScope.launch(Dispatchers.Main.immediate) {
            _sideEffect.send(sideEffect)
        }
    }
}