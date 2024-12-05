package com.coding.exercise.receipesearch.domain.repository

import com.coding.exercise.receipesearch.data.dto.RecipeResponse

interface RecipeRepository {
    suspend fun searchRecipes(query:String): RecipeResponse
    suspend fun getRecipeDetails(id:String):RecipeResponse
}