package com.example.gioiovashvili.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@kotlinx.serialization.Serializable
data class RecipeResponseDto(
    @SerialName("recipes")
    val recipes: List<RecipeDto>
)

@Serializable
data class RecipeDto(
    @SerialName("id")
    val id: String,
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val description: String,
    @SerialName("imageUrl")
    val imageUrl: String,
    @SerialName("category")
    val category: String,
    @SerialName("prepTimeMinutes")
    val prepTimeMinutes: Int,
    @SerialName("cookTimeMinutes")
    val cookTimeMinutes: Int,
    @SerialName("servings")
    val servings: Int,
    @SerialName("difficulty")
    val difficulty: String,
    @SerialName("rating")
    val rating: Double,
    @SerialName("ingredients")
    val ingredients: List<String>,
    @SerialName("instructions")
    val instructions: List<String>
)