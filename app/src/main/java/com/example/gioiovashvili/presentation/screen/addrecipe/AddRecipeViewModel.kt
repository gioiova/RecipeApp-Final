package com.example.gioiovashvili.presentation.screen.addrecipe

import androidx.lifecycle.viewModelScope
import com.example.gioiovashvili.domain.model.Recipe
import com.example.gioiovashvili.domain.usecase.AddRecipeUseCase
import com.example.gioiovashvili.presentation.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class AddRecipeViewModel @Inject constructor(
    private val addRecipeUseCase: AddRecipeUseCase
) : BaseViewModel<AddRecipeState, AddRecipeSideEffect>(AddRecipeState()) {

    fun onTitleChanged(value: String) = updateField { it.copy(title = value, error = null) }
    fun onDescriptionChanged(value: String) = updateField { it.copy(description = value, error = null) }
    fun onImageUrlChanged(value: String) = updateField { it.copy(imageUrl = value, error = null) }
    fun onPrepTimeChanged(value: String) = updateField { it.copy(prepTimeMinutes = value, error = null) }
    fun onCookTimeChanged(value: String) = updateField { it.copy(cookTimeMinutes = value, error = null) }
    fun onDifficultyChanged(value: String) = updateField { it.copy(difficulty = value, error = null) }
    fun onRatingChanged(value: String) = updateField { it.copy(rating = value, error = null) }
    fun onIngredientsChanged(value: String) = updateField { it.copy(ingredientsText = value, error = null) }
    fun onInstructionsChanged(value: String) = updateField { it.copy(instructionsText = value, error = null) }

    fun onBackClick() {
        emitSideEffect(AddRecipeSideEffect.NavigateBack)
    }

    fun onSaveClick() {
        val currentState = state.value
        val validationError = validate(currentState)
        if (validationError != null) {
            updateState { it.copy(error = validationError) }
            return
        }

        viewModelScope.launch {
            updateState { it.copy(isSaving = true, error = null) }

            try {
                val recipe = buildRecipe(currentState)
                addRecipeUseCase(recipe)
                emitSideEffect(AddRecipeSideEffect.ShowToast("Recipe saved successfully!"))
                emitSideEffect(AddRecipeSideEffect.NavigateBack)
            } catch (e: Exception) {
                updateState {
                    it.copy(
                        isSaving = false,
                        error = e.localizedMessage ?: "Failed to save recipe"
                    )
                }
            }
        }
    }

    private fun updateField(update: (AddRecipeState) -> AddRecipeState) {
        updateState(update)
    }

    private fun validate(state: AddRecipeState): String? {
        if (state.title.isBlank()) return "Title is required"
        if (state.description.isBlank()) return "Description is required"

        val prepTime = state.prepTimeMinutes.toIntOrNull()
        if (prepTime == null || prepTime < 0) return "Enter a valid prep time"

        val cookTime = state.cookTimeMinutes.toIntOrNull()
        if (cookTime == null || cookTime < 0) return "Enter a valid cook time"

        if (prepTime + cookTime == 0) return "Total cooking time must be greater than 0"

        val rating = state.rating.toDoubleOrNull()
        if (rating == null || rating !in 0.0..5.0) return "Rating must be between 0 and 5"

        val ingredients = parseLines(state.ingredientsText)
        if (ingredients.isEmpty()) return "Add at least one ingredient"

        val instructions = parseLines(state.instructionsText)
        if (instructions.isEmpty()) return "Add at least one instruction step"

        return null
    }

    private fun buildRecipe(state: AddRecipeState): Recipe {
        val prepTime = state.prepTimeMinutes.toInt()
        val cookTime = state.cookTimeMinutes.toInt()

        return Recipe(
            id = "user-${UUID.randomUUID()}",
            title = state.title.trim(),
            description = state.description.trim(),
            imageUrl = state.imageUrl.trim().ifBlank {
                "https://images.unsplash.com/photo-1495521821757-a1efb6729352?w=800"
            },
            totalTimeMinutes = prepTime + cookTime,
            difficulty = state.difficulty,
            rating = state.rating.toDouble(),
            ingredients = parseLines(state.ingredientsText),
            instructions = parseLines(state.instructionsText)
        )
    }

    private fun parseLines(text: String): List<String> {
        return text.lines()
            .map { it.trim() }
            .filter { it.isNotBlank() }
    }
}
