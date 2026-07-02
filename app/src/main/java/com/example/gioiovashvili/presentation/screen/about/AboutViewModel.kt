package com.example.gioiovashvili.presentation.screen.about

import com.example.gioiovashvili.presentation.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AboutViewModel @Inject constructor() :
    BaseViewModel<AboutState, AboutSideEffect>(AboutState()) {

    init {
        updateState {
            it.copy(
                features = listOf(
                    AboutFeature(
                        emoji = "🍲",
                        title = "Recipe Collection",
                        description = "Browse a curated list of delicious recipes with photos, ratings, and cooking times."
                    ),
                    AboutFeature(
                        emoji = "🔍",
                        title = "Smart Search",
                        description = "Quickly find recipes by title or description using the built-in search bar."
                    ),
                    AboutFeature(
                        emoji = "💾",
                        title = "Offline Ready",
                        description = "Recipes are cached locally with Room, so your favorites stay available even offline."
                    )
                ),
                techStack = listOf(
                    "Jetpack Compose",
                    "MVVM + Hilt",
                    "Room Database",
                    "Retrofit API"
                )
            )
        }
    }

    fun onBackClick() {
        emitSideEffect(AboutSideEffect.NavigateBack)
    }
}
