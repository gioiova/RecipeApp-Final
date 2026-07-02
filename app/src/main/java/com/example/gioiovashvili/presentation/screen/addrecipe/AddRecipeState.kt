package com.example.gioiovashvili.presentation.screen.addrecipe

data class AddRecipeState(
    val title: String = "",
    val description: String = "",
    val imageUrl: String = "",
    val prepTimeMinutes: String = "",
    val cookTimeMinutes: String = "",
    val difficulty: String = "Easy",
    val rating: String = "4.5",
    val ingredientsText: String = "",
    val instructionsText: String = "",
    val isSaving: Boolean = false,
    val error: String? = null
) {
    val difficultyOptions: List<String> = listOf("Easy", "Medium", "Hard")
}
