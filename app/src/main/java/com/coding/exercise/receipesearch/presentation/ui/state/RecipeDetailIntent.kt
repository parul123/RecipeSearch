package com.coding.exercise.receipesearch.presentation.ui.state

sealed class RecipeDetailIntent {
    data class getRecipeDetail(val id: String) : RecipeDetailIntent()
}