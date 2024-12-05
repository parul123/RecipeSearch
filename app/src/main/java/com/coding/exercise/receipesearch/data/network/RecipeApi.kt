package com.coding.exercise.receipesearch.data.network

import com.coding.exercise.receipesearch.data.dto.RecipeResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeApi {

    @GET("api/json/v1/1/search.php")
    suspend fun searchRecipes(
        @Query("s") query: String
    ): RecipeResponse

    @GET("api/json/v1/1/lookup.php")
    suspend fun getRecipeDetails(
        @Query("i") i: String
    ): RecipeResponse
}