package com.coding.exercise.receipesearch.domain.usecase

import com.coding.exercise.receipesearch.common.UiState
import com.coding.exercise.receipesearch.data.dto.RecipeDto
import com.coding.exercise.receipesearch.data.dto.RecipeResponse
import com.coding.exercise.receipesearch.data.repository.FakeRepository
import com.coding.exercise.receipesearch.domain.model.Recipe
import com.coding.exercise.receipesearch.domain.repository.RecipeRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

@ExperimentalCoroutinesApi
class SearchRecipeUseCaseTest {
    private lateinit var useCase: SearchRecipeUseCase

    private lateinit var repository: RecipeRepository
    @Mock
    private lateinit var recipeResponse: RecipeResponse

    @Before
    fun setUp() {
        repository = FakeRepository()
        useCase = SearchRecipeUseCase(repository)
        recipeResponse = mock(RecipeResponse::class.java)
    }

    @Test
    fun `test search success for pasta`() = runTest {
        val query = "pasta"
        val result = useCase.invoke(query)
        result.collect { uiState ->
            when (uiState) {
                is UiState.Loading -> {
                    // Assert loading state
                    assert(true) // Just verify that it's emitting the loading state
                }
                is UiState.Success -> {
                    // Assert success
                    uiState.data?.let { assert(it.isNotEmpty()) }
                    assertEquals("Pasta", uiState.data?.first()?.name ?:"" )
                }
                else -> assert(false)
            }
        }
    }

    @Test
    fun `test search with no meals found`() = runTest {
        // Given
        val query = "rajma"

        // Collect the flow
        val result = useCase.invoke(query)

        // Then
        result.collect { uiState ->
            when (uiState) {
                is UiState.Loading -> {
                    // Assert loading state
                    assert(true)
                }
                is UiState.Success -> {
                    // Assert success with empty data
                    uiState.data?.let { assert(it.isEmpty()) }
                }
                else -> assert(false)
            }
        }
    }

}