package com.coding.exercise.receipesearch.presentation.ui.state

import com.coding.exercise.receipesearch.domain.model.Recipe


sealed class RecipeViewState {
    object Init: RecipeViewState()
    object Loading: RecipeViewState()
    data class Success(val recipes: List<Recipe>): RecipeViewState()
    data class Error(val message: String): RecipeViewState()
    object NetworkError: RecipeViewState()
}