package com.example.gioiovashvili.presentation.screen.home

import androidx.lifecycle.viewModelScope
import com.example.gioiovashvili.domain.common.Resource
import com.example.gioiovashvili.domain.model.Recipe
import com.example.gioiovashvili.domain.usecase.GetRecipesUseCase
import com.example.gioiovashvili.presentation.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getRecipesUseCase: GetRecipesUseCase
) : BaseViewModel<HomeState, HomeSideEffect>(HomeState()) {
    private var allRecipes: List<Recipe> = emptyList()

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
                        val data = resource.data ?: emptyList()
                        allRecipes = data

                        updateState {
                            it.copy(
                                recipes = filterRecipes(data, it.searchQuery),
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
                        emitSideEffect(HomeSideEffect.ShowToast(resource.message ?: "Unknown Error"))
                    }
                }
            }
        }
    }

    fun onSearchQueryChanged(query: String) {
        updateState {
            it.copy(
                searchQuery = query,
                recipes = filterRecipes(allRecipes, query)
            )
        }
    }

    fun onAboutClick() {
        emitSideEffect(HomeSideEffect.NavigateToAbout)
    }

    fun onAddRecipeClick() {
        emitSideEffect(HomeSideEffect.NavigateToAddRecipe)
    }

    fun onLogoutClick() {
        emitSideEffect(HomeSideEffect.NavigateToLogin)
    }
    private fun filterRecipes(recipes: List<Recipe>, query: String): List<Recipe> {
        if (query.isBlank()) return recipes
        return recipes.filter { recipe ->
            recipe.title.contains(query, ignoreCase = true) ||
                    recipe.description.contains(query, ignoreCase = true)
        }
    }
}