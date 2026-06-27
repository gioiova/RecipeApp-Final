package com.example.gioiovashvili.data.repository

import com.example.gioiovashvili.data.apiService.GetRecipesApiService
import com.example.gioiovashvili.data.common.HandleResponse
import com.example.gioiovashvili.data.database.dao.RecipeDao
import com.example.gioiovashvili.data.mappers.toDomain
import com.example.gioiovashvili.data.mappers.toEntity
import com.example.gioiovashvili.domain.common.Resource
import com.example.gioiovashvili.domain.model.Recipe
import com.example.gioiovashvili.domain.repository.RecipesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.map

class RecipesRepositoryImpl @Inject constructor(
    private val apiService: GetRecipesApiService,
    private val recipeDao: RecipeDao,
    private val handleResponse: HandleResponse
) : RecipesRepository {

    override suspend fun getRecipes(): Flow<Resource<List<Recipe>>> = flow {
        emit(Resource.Loading(loading = true))

        handleResponse.safeApiCall { apiService.getFields() }.collect { resource ->
            when (resource) {
                is Resource.Success -> {
                    val remoteDto = resource.data
                    val entities = remoteDto.recipes.map { it.toEntity() }
                    recipeDao.insertRecipes(entities)
                    emit(Resource.Loading(loading = false))
                }
                is Resource.Error -> {
                    emit(Resource.Loading(loading = false))
                    emit(Resource.Error(message = resource.message))
                }
                is Resource.Loading -> {
                    emit(Resource.Loading(loading = resource.loading))
                }
            }
        }
        val localRecipesFlow = recipeDao.getRecipes().map { entities ->
            Resource.Success(data = entities.map { it.toDomain() })
        }

        emitAll(localRecipesFlow)
    }
}