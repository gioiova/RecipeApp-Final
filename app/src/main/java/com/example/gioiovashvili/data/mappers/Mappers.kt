package com.example.gioiovashvili.data.mappers

import com.example.gioiovashvili.data.database.entity.RecipeEntity
import com.example.gioiovashvili.data.dto.RecipeDto
import com.example.gioiovashvili.domain.model.Recipe

fun RecipeDto.toEntity(): RecipeEntity {
    return RecipeEntity(
        id = this.id,
        title = this.title,
        description = this.description,
        imageUrl = this.imageUrl,
        prepTimeMinutes = this.prepTimeMinutes,
        cookTimeMinutes = this.cookTimeMinutes,
        difficulty = this.difficulty,
        rating = this.rating,
        ingredientsRaw = this.ingredients.joinToString("||"),
        instructionsRaw = this.instructions.joinToString("||")
    )
}

fun RecipeEntity.toDomain(): Recipe {
    return Recipe(
        id = this.id,
        title = this.title,
        description = this.description,
        imageUrl = this.imageUrl,
        totalTimeMinutes = this.prepTimeMinutes + this.cookTimeMinutes,
        difficulty = this.difficulty,
        rating = this.rating,
        ingredients = this.ingredientsRaw.split("||").filter { it.isNotBlank() },
        instructions = this.instructionsRaw.split("||").filter { it.isNotBlank() }
    )
}