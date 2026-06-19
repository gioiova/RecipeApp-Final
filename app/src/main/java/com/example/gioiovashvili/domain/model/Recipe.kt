package com.example.gioiovashvili.domain.model

data class Recipe (
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: String,
    val totalTimeMinutes: Int,
    val difficulty: String,
    val rating: Double,
    val ingredients: List<String>,
    val instructions: List<String>
)