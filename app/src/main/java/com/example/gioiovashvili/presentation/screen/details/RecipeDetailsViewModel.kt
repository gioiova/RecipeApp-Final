package com.example.gioiovashvili.presentation.screen.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.gioiovashvili.domain.usecase.GetRecipeByIdUseCase
import com.example.gioiovashvili.presentation.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailsViewModel @Inject constructor(
    private val getRecipeByIdUseCase: GetRecipeByIdUseCase,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<RecipeDetailsState, RecipeDetailsSideEffect>(RecipeDetailsState()) {

    private val recipeId: String = checkNotNull(savedStateHandle["recipeId"])

    init {
        loadRecipe()
    }

    private fun loadRecipe() {
        viewModelScope.launch {
            updateState { it.copy(isLoading = true) }
            val recipe = getRecipeByIdUseCase(recipeId)
            if (recipe != null) {
                updateState { it.copy(isLoading = false, recipe = recipe) }
            } else {
                updateState { it.copy(isLoading = false, error = "Recipe not found") }
            }
        }
    }

    fun onBackClick() {
        emitSideEffect(RecipeDetailsSideEffect.NavigateBack)
    }
}