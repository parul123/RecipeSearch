package com.coding.exercise.receipesearch.presentation.ui.state

import com.coding.exercise.receipesearch.domain.model.RecipeDetail

sealed class RecipeDetailState {
    object Loading: RecipeDetailState()
    data class Success(val recipes: RecipeDetail): RecipeDetailState()
    data class Error(val message: String): RecipeDetailState()
    object NetworkError: RecipeDetailState()
}