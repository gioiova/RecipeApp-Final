package com.example.gioiovashvili.data.apiService

import com.example.gioiovashvili.data.dto.RecipeResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface GetRecipesApiService {
    @GET("50b30c38-bf0f-45e3-aea9-42bd77a61e0d")
    suspend fun getFields(): Response<RecipeResponseDto>
}