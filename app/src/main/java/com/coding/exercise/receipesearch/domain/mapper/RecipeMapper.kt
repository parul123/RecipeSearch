package com.coding.exercise.receipesearch.domain.mapper

import com.coding.exercise.receipesearch.data.dto.RecipeDto
import com.coding.exercise.receipesearch.domain.model.Recipe
import com.coding.exercise.receipesearch.domain.model.RecipeDetail

object RecipeMapper {

    fun toDomainMeal(recipeDto: RecipeDto): Recipe {
        return Recipe(
            id = recipeDto.idMeal,
            name = recipeDto.strMeal ?: "",
            image = recipeDto.strMealThumb ?: ""
        )
    }

    fun toDomainRecipeDetail(recipeDto: RecipeDto): RecipeDetail {
        return RecipeDetail(
            id = recipeDto.idMeal,
            name = recipeDto.strMeal ?: "",
            image = recipeDto.strMealThumb ?: "",
            instructions = recipeDto.strInstructions ?: "",
            category = recipeDto.strCategory ?: "",
            ingredient1 = recipeDto.strIngredient1 ?: "",
            ingredient2 = recipeDto.strIngredient2 ?: "",
            ingredient3 = recipeDto.strIngredient3 ?: "",
            ingredient4 = recipeDto.strIngredient4 ?: "",
            ingredient5 = recipeDto.strIngredient5 ?: "",
            ingredient6 = recipeDto.strIngredient6 ?: "",
            ingredient7 = recipeDto.strIngredient7 ?: "",
            ingredient8 = recipeDto.strIngredient8 ?: "",
            ingredient9 = recipeDto.strIngredient9 ?: "",
            ingredient10 = recipeDto.strIngredient10 ?: "",
            ingredient11 = recipeDto.strIngredient11 ?: "",
            ingredient12 = recipeDto.strIngredient12 ?: "",
            ingredient13 = recipeDto.strIngredient13 ?: "",
            ingredient14 = recipeDto.strIngredient14 ?: "",
            ingredient15 = recipeDto.strIngredient15 ?: "",
            ingredient16 = recipeDto.strIngredient16 ?: "",
            ingredient17 = recipeDto.strIngredient17 ?: "",
            ingredient18 = recipeDto.strIngredient18 ?: "",
            ingredient19 = recipeDto.strIngredient19 ?: "",
            ingredient20 = recipeDto.strIngredient20 ?: "",

            measure1 = recipeDto.strMeasure1 ?: "",
            measure2 = recipeDto.strMeasure2 ?: "",
            measure3 = recipeDto.strMeasure3 ?: "",
            measure4 = recipeDto.strMeasure4 ?: "",
            measure5 = recipeDto.strMeasure5 ?: "",
            measure6 = recipeDto.strMeasure6 ?: "",
            measure7 = recipeDto.strMeasure7 ?: "",
            measure8 = recipeDto.strMeasure8 ?: "",
            measure9 = recipeDto.strMeasure9 ?: "",
            measure10 = recipeDto.strMeasure10 ?: "",
            measure11 = recipeDto.strMeasure11 ?: "",
            measure12 = recipeDto.strMeasure12 ?: "",
            measure13 = recipeDto.strMeasure13 ?: "",
            measure14 = recipeDto.strMeasure14 ?: "",
            measure15 = recipeDto.strMeasure15 ?: "",
            measure16 = recipeDto.strMeasure16 ?: "",
            measure17 = recipeDto.strMeasure17 ?: "",
            measure18 = recipeDto.strMeasure18 ?: "",
            measure19 = recipeDto.strMeasure19 ?: "",
            measure20 = recipeDto.strMeasure20 ?: ""
            )
    }
}