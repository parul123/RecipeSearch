package com.coding.exercise.receipesearch.domain.usecase

import com.coding.exercise.receipesearch.common.UiState
import com.coding.exercise.receipesearch.data.dto.RecipeDto
import com.coding.exercise.receipesearch.data.dto.RecipeResponse
import com.coding.exercise.receipesearch.domain.mapper.RecipeMapper
import com.coding.exercise.receipesearch.domain.model.RecipeDetail
import com.coding.exercise.receipesearch.domain.repository.RecipeRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.HttpException
import java.io.IOException
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
class GetRecipeDetailUseCaseTest {

    @Mock
    private lateinit var recipeRepository: RecipeRepository

    private lateinit var getRecipeDetailUseCase: GetRecipeDetailUseCase


    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        getRecipeDetailUseCase = GetRecipeDetailUseCase(recipeRepository)
    }

    @Test
    fun `test invoke should return loading and success state`() = runBlocking{
        val recipeId = "52777"
        val expectedResponse = RecipeResponse(meals = listOf(
            RecipeDto(
                dateModified = null,
                idMeal = "52777",
                strArea = "Italian",
                strCategory = "Seafood",
                strCreativeCommonsConfirmed = null,
                strDrinkAlternate = null,
                strImageSource = "https://example.com/image.jpg",
                strIngredient1 = "mozzarella balls",
                strIngredient10 = "",
                strIngredient11 = "",
                strIngredient12 = "",
                strIngredient13 = "",
                strIngredient14 = "",
                strIngredient15 = "",
                strIngredient16 = null,
                strIngredient17 = null,
                strIngredient18 = null,
                strIngredient19 = null,
                strIngredient2 = null,
                strIngredient20 = null,
                strIngredient3 = "fresh basil",
                strIngredient4 = "farfalle",
                strIngredient5 = "extra virgin olive oil",
                strIngredient6 = "Green Olives",
                strIngredient7 = "tuna",
                strIngredient8 = "salt",
                strIngredient9 = "pepper",
                strInstructions = "Bring a large saucepan of salted water to the boil\r\nAdd the pasta, stir once and cook for about 10 minutes or as directed on the packet.\r\nMeanwhile, wash the tomatoes and cut into quarters. Slice the olives. Wash the basil.\r\nPut the tomatoes into a salad bowl and tear the basil leaves over them. Add a tablespoon of olive oil and mix.\r\nWhen the pasta is ready, drain into a colander and run cold water over it to cool it quickly.\r\nToss the pasta into the salad bowl with the tomatoes and basil.\r\nAdd the sliced olives, drained mozzarella balls, and chunks of tuna. Mix well and let the salad rest for at least half an hour to allow the flavours to mingle.\r\nSprinkle the pasta with a generous grind of black pepper and drizzle with the remaining olive oil just before serving.",
                strMeal = "Mediterranean Pasta Salad",
                strMealThumb = "https://www.themealdb.com/images/media/meals/wvqpwt1468339226.jpg",
                strMeasure1 = "200 g",
                strMeasure10 = "",
                strMeasure11 = "",
                strMeasure12 = "",
                strMeasure13 = "",
                strMeasure14 = "",
                strMeasure15 = "",
                strMeasure16 = null,
                strMeasure17 = null,
                strMeasure18 = null,
                strMeasure19 = null,
                strMeasure2 = "250 g",
                strMeasure20 = null,
                strMeasure3 = "1 bunch",
                strMeasure4 = "350 g",
                strMeasure5 = "3  tablespoons",
                strMeasure6 = "40 g",
                strMeasure7 = "200 g",
                strMeasure8 = "to taste",
                strMeasure9 = "to taste",
                strSource = "https://example.com/recipe_source",
                strTags = "Pasta,Baking",
                strYoutube = "https://www.youtube.com/watch?v=e52IL8zYmaE")
        ))
        val expectedRecipeDetails = expectedResponse.meals!!.map { RecipeMapper.toDomainRecipeDetail(it) }

        Mockito.`when`(recipeRepository.getRecipeDetails(recipeId)).thenReturn(expectedResponse)
        val result = getRecipeDetailUseCase.invoke(recipeId)

        result.collect { uiState ->
            when (uiState) {
                is UiState.Loading -> {
                    assertTrue(true)
                }
                is UiState.Success -> {
                    assertEquals(uiState.data, expectedRecipeDetails)
                }
                else -> {}
            }
        }
    }

    @Test
    fun `test invoke should return error state when HttpException occurs`() = runBlocking{
        // Arrange
        val recipeId = "52777"
        val errorMessage = "An Unknown error occurred"
        Mockito.`when`(recipeRepository.getRecipeDetails(recipeId)).thenThrow(HttpException::class.java)

        val result = getRecipeDetailUseCase.invoke(recipeId)

        result.collect { uiState ->
            when (uiState) {
                is UiState.Error -> {
                    assertEquals(uiState.message, errorMessage)
                }
                else -> {}
            }
        }
    }

    @Test
    fun `test invoke should return no network state when IOException occurs`() = runBlocking{
        // Arrange
        val recipeId = "52777"
        Mockito.`when`(recipeRepository.getRecipeDetails(recipeId)).thenThrow(IOException::class.java)
        val result = getRecipeDetailUseCase.invoke(recipeId)
        result.collect { uiState ->
            when (uiState) {
                is UiState.NoNetwork -> {
                    assertTrue(true)
                }
                else -> {}
            }
        }
    }

    @Test
    fun `test invoke should return empty list when no meals are found`() = runBlocking{
        // Arrange
        val recipeId = "52777"
        val response = RecipeResponse(meals = null)
        val expectedRecipeDetails = emptyList<RecipeDetail>()

        Mockito.`when`(recipeRepository.getRecipeDetails(recipeId)).thenReturn(response)

        // Act
        val result = getRecipeDetailUseCase.invoke(recipeId)

        // Assert
        result.collect { uiState ->
            when (uiState) {
                is UiState.Success -> {
                    assertEquals(uiState.data, expectedRecipeDetails)
                }
                else -> {}
            }
        }
    }
}
