package com.example.gioiovashvili.presentation.screen.home

import androidx.lifecycle.viewModelScope
import com.example.gioiovashvili.domain.common.Resource
import com.example.gioiovashvili.domain.usecase.GetRecipesUseCase
import com.example.gioiovashvili.presentation.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getRecipesUseCase: GetRecipesUseCase
) : BaseViewModel<HomeState, HomeSideEffect>(HomeState()) {

    init {
        getRecipes()
    }

    private fun getRecipes() {
        viewModelScope.launch {
            getRecipesUseCase().collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        updateState {
                            it.copy(
                                isLoading = resource.loading,
                                error = null
                            )
                        }
                    }
                    is Resource.Success -> {
                        updateState {
                            it.copy(
                                recipes = resource.data,
                                isLoading = false,
                                error = null
                            )
                        }
                    }
                    is Resource.Error -> {
                        updateState {
                            it.copy(
                                isLoading = false,
                                error = resource.message
                            )
                        }
                        emitSideEffect(HomeSideEffect.ShowToast(resource.message))
                    }
                }
            }
        }
    }
}