package com.example.gioiovashvili.presentation.screen.home

import com.example.gioiovashvili.domain.model.Recipe

data class HomeState(
    val recipes: List<Recipe> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val searchQuery: String = ""
)