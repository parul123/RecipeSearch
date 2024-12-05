package com.coding.exercise.receipesearch.presentation.ui.state

sealed class RecipeSearchIntent {
    data class SearchRecipes(val query: String) : RecipeSearchIntent()
}