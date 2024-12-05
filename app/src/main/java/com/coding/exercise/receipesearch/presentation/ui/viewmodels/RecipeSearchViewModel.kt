package com.coding.exercise.receipesearch.presentation.ui.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coding.exercise.receipesearch.common.UiState
import com.coding.exercise.receipesearch.domain.usecase.SearchRecipeUseCase
import com.coding.exercise.receipesearch.presentation.ui.state.RecipeSearchIntent
import com.coding.exercise.receipesearch.presentation.ui.state.RecipeViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.compose.runtime.State
import com.coding.exercise.receipesearch.common.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn

import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class RecipeSearchViewModel @Inject constructor(
    private val searchRecipeUseCase: SearchRecipeUseCase
) : ViewModel() {
    private val _state = mutableStateOf<RecipeViewState>(RecipeViewState.Init)
    val state: State<RecipeViewState> = _state
    fun processIntent(intent: RecipeSearchIntent) {
        when(intent) {
            is RecipeSearchIntent.SearchRecipes -> {
                viewModelScope.launch {
                    searchRecipe(intent.query)
                }
            }
        }
    }

    private suspend fun searchRecipe(query: String) {
        searchRecipeUseCase.invoke(query).collect{
            when (it) {
                is UiState.Loading -> {
                    _state.value =  RecipeViewState.Loading
                }
                is UiState.Success -> {

                    _state.value = it.data?.let { it1 -> RecipeViewState.Success(it1) }!!
                }
                is UiState.Error -> {
                    _state.value = RecipeViewState.Error("Error in fetching receipes")
                }
                is UiState.NoNetwork -> {
                    _state.value = RecipeViewState.NetworkError
                }
            }
        }
    }
}