package com.coding.exercise.receipesearch.presentation.ui.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coding.exercise.receipesearch.common.UiState
import com.coding.exercise.receipesearch.domain.usecase.GetRecipeDetailUseCase
import com.coding.exercise.receipesearch.presentation.ui.state.RecipeDetailIntent
import com.coding.exercise.receipesearch.presentation.ui.state.RecipeDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class RecipeDetailScreenModel  @Inject constructor(private val recipeDetailsUseCase: GetRecipeDetailUseCase) :ViewModel() {
    private val _state = mutableStateOf<RecipeDetailState>(RecipeDetailState.Loading)
    val state: State<RecipeDetailState> = _state

    fun processIntent(intent: RecipeDetailIntent) {
        when(intent) {
            is RecipeDetailIntent.getRecipeDetail -> {
                viewModelScope.launch {
                    getRecipeDetail(intent.id)
                }
            }
        }
    }
    private suspend fun getRecipeDetail(id: String) {
        recipeDetailsUseCase.invoke(id).collect {
            when (it) {
                is UiState.Loading -> {

                    _state.value = RecipeDetailState.Loading
                }
                is UiState.Error -> {
                    _state.value = RecipeDetailState.Error("Error in Loading Recipe detail")
                }
                is UiState.NoNetwork -> {
                    _state.value = RecipeDetailState.NetworkError
                }
                is UiState.Success -> {
                    _state.value = it.data?.let { it1 -> RecipeDetailState.Success(it1.get(0)) }!!
                }
            }
        }
    }
}