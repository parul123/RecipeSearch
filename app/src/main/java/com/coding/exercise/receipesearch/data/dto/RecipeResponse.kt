package com.coding.exercise.receipesearch.data.dto

import com.google.gson.annotations.SerializedName

data class RecipeResponse(
    @SerializedName("meals")
    var meals: List<RecipeDto>?
)