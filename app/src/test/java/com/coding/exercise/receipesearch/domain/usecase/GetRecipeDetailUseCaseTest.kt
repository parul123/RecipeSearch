package com.coding.exercise.receipesearch.domain.usecase

import com.coding.exercise.receipesearch.common.UiState
import com.coding.exercise.receipesearch.data.repository.FakeRepository
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals

class GetRecipeDetailUseCaseTest {
    private lateinit var getRecipeDetailUseCase: GetRecipeDetailUseCase

    private lateinit var repository: FakeRepository
    @Before
    fun setup() {
        // Initialize the fake repository
        repository = FakeRepository()
        getRecipeDetailUseCase = GetRecipeDetailUseCase(repository)
    }

    @Test
    fun `test get recipe details success for pasta`() = runTest {
        // Given
        val id = "1" // Valid ID for "Pasta Carbonara"
        val result = getRecipeDetailUseCase.invoke(id)
        result.collect { uiState ->
            when (uiState) {
                is UiState.Loading -> {
                    // Assert loading state
                    assert(true)
                }
                is UiState.Success -> {
                    // Assert success
                    uiState.data?.let { assert(it.isNotEmpty()) }
                    assertEquals("Pasta Carbonara", uiState.data?.first()?.name ?:"" )
                }
                else -> assert(false)
            }
        }
    }

    @Test
    fun `test get recipe details with no meals found`() = runTest {
        // Given
        val id = "999" // Invalid ID for non-existent recipe

        // Collect the flow
        val result = getRecipeDetailUseCase.invoke(id)

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