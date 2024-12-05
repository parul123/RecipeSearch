package com.coding.exercise.receipesearch.data.repository

import com.coding.exercise.receipesearch.data.dto.RecipeDto
import com.coding.exercise.receipesearch.data.dto.RecipeResponse
import com.coding.exercise.receipesearch.data.network.RecipeApi
import com.coding.exercise.receipesearch.domain.repository.RecipeRepository

class RecipeRepositoryImpl(private val recipeApi: RecipeApi) : RecipeRepository {
    override suspend fun searchRecipes(query: String): RecipeResponse {
        return recipeApi.searchRecipes(query)
    }

    override suspend fun getRecipeDetails(id: String): RecipeResponse {
        return recipeApi.getRecipeDetails(id)
    }


}