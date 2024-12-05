package com.coding.exercise.receipesearch.data.repository

import com.coding.exercise.receipesearch.data.dto.RecipeDto
import com.coding.exercise.receipesearch.data.dto.RecipeResponse
import com.coding.exercise.receipesearch.domain.repository.RecipeRepository

class FakeRepository : RecipeRepository {

    // This is the fake implementation of the searchRecipes function.
    override suspend fun searchRecipes(query: String): RecipeResponse {
        return when (query) {
            "pasta" -> RecipeResponse(
                meals = listOf(
                    RecipeDto(
                        dateModified = "2024-12-01",
                        idMeal = "12345",
                        strArea = "Italian",
                        strCategory = "Main Course",
                        strCreativeCommonsConfirmed = "Yes",
                        strDrinkAlternate = "Water",
                        strImageSource = "https://example.com/image.jpg",
                        strIngredient1 = "Tomato",
                        strIngredient10 = "Cheese",
                        strIngredient11 = "Olives",
                        strIngredient12 = "Basil",
                        strIngredient13 = "Garlic",
                        strIngredient14 = "Onion",
                        strIngredient15 = "Mushrooms",
                        strIngredient16 = "Pepper",
                        strIngredient17 = "Salt",
                        strIngredient18 = "Oregano",
                        strIngredient19 = "Olive Oil",
                        strIngredient2 = "Pasta",
                        strIngredient20 = "Parmesan",
                        strIngredient3 = "Chicken",
                        strIngredient4 = "Bacon",
                        strIngredient5 = "Beef",
                        strIngredient6 = "Eggs",
                        strIngredient7 = "Lemon",
                        strIngredient8 = "Cucumber",
                        strIngredient9 = "Bell Pepper",
                        strInstructions = "Cook pasta, grill chicken, and mix all ingredients with seasoning.",
                        strMeal = "Chicken Pasta",
                        strMealThumb = "https://example.com/meal_thumb.jpg",
                        strMeasure1 = "2 cups",
                        strMeasure10 = "1 cup",
                        strMeasure11 = "1/2 cup",
                        strMeasure12 = "2 tbsp",
                        strMeasure13 = "1 tsp",
                        strMeasure14 = "1/4 cup",
                        strMeasure15 = "3 slices",
                        strMeasure16 = "1 tbsp",
                        strMeasure17 = "1/2 tbsp",
                        strMeasure18 = "1/2 tsp",
                        strMeasure19 = "2 tbsp",
                        strMeasure2 = "1 lb",
                        strMeasure20 = "1/4 cup",
                        strMeasure3 = "200g",
                        strMeasure4 = "100g",
                        strMeasure5 = "150g",
                        strMeasure6 = "2 large",
                        strMeasure7 = "1 small",
                        strMeasure8 = "1/2 cup",
                        strMeasure9 = "1/2 cup",
                        strSource = "https://example.com/recipe_source",
                        strTags = "Pasta, Italian, Dinner",
                        strYoutube = "https://youtube.com/watch?v=dQw4w9WgXcQ"
                    )
            ))
            else -> RecipeResponse(meals = null) // Simulate no meals found
        }
    }

    override suspend fun getRecipeDetails(id: String): RecipeResponse {
        return when (id) {
            "1" -> RecipeResponse(
                meals = listOf(
                    RecipeDto(
                        dateModified = "2024-12-01",
                        idMeal = "12345",
                        strArea = "Italian",
                        strCategory = "Main Course",
                        strCreativeCommonsConfirmed = "Yes",
                        strDrinkAlternate = "Water",
                        strImageSource = "https://example.com/image.jpg",
                        strIngredient1 = "Tomato",
                        strIngredient10 = "Cheese",
                        strIngredient11 = "Olives",
                        strIngredient12 = "Basil",
                        strIngredient13 = "Garlic",
                        strIngredient14 = "Onion",
                        strIngredient15 = "Mushrooms",
                        strIngredient16 = "Pepper",
                        strIngredient17 = "Salt",
                        strIngredient18 = "Oregano",
                        strIngredient19 = "Olive Oil",
                        strIngredient2 = "Pasta",
                        strIngredient20 = "Parmesan",
                        strIngredient3 = "Chicken",
                        strIngredient4 = "Bacon",
                        strIngredient5 = "Beef",
                        strIngredient6 = "Eggs",
                        strIngredient7 = "Lemon",
                        strIngredient8 = "Cucumber",
                        strIngredient9 = "Bell Pepper",
                        strInstructions = "Cook pasta, grill chicken, and mix all ingredients with seasoning.",
                        strMeal = "Chicken Pasta",
                        strMealThumb = "https://example.com/meal_thumb.jpg",
                        strMeasure1 = "2 cups",
                        strMeasure10 = "1 cup",
                        strMeasure11 = "1/2 cup",
                        strMeasure12 = "2 tbsp",
                        strMeasure13 = "1 tsp",
                        strMeasure14 = "1/4 cup",
                        strMeasure15 = "3 slices",
                        strMeasure16 = "1 tbsp",
                        strMeasure17 = "1/2 tbsp",
                        strMeasure18 = "1/2 tsp",
                        strMeasure19 = "2 tbsp",
                        strMeasure2 = "1 lb",
                        strMeasure20 = "1/4 cup",
                        strMeasure3 = "200g",
                        strMeasure4 = "100g",
                        strMeasure5 = "150g",
                        strMeasure6 = "2 large",
                        strMeasure7 = "1 small",
                        strMeasure8 = "1/2 cup",
                        strMeasure9 = "1/2 cup",
                        strSource = "https://example.com/recipe_source",
                        strTags = "Pasta, Italian, Dinner",
                        strYoutube = "https://youtube.com/watch?v=dQw4w9WgXcQ"
                    )
                ))
            else -> RecipeResponse(meals = null) // Simulate no meals found
        }
    }
}
