package com.example.gioiovashvili.presentation.screen.about

data class AboutFeature(
    val emoji: String,
    val title: String,
    val description: String
)

data class AboutState(
    val appName: String = "YumRecipe",
    val tagline: String = "Discover, search, and enjoy recipes from around the world.",
    val version: String = "1.0.0",
    val features: List<AboutFeature> = emptyList(),
    val techStack: List<String> = emptyList()
)
