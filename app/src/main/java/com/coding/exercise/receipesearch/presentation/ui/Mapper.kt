package com.coding.exercise.receipesearch.presentation.ui

import com.coding.exercise.receipesearch.domain.model.RecipeDetail

object Mapper {

    fun mapDomainToDetailView(meal: RecipeDetail): String {
        var ingredients = ""

        with(meal) {
            if (!ingredient1.isNullOrEmpty()) ingredients += "$ingredient1 - $measure1\n"
            if (!ingredient2.isNullOrEmpty()) ingredients += "$ingredient2 - $measure2\n"
            if (!ingredient3.isNullOrEmpty()) ingredients += "$ingredient3 - $measure3\n"
            if (!ingredient4.isNullOrEmpty()) ingredients += "$ingredient4 - $measure4\n"
            if (!ingredient5.isNullOrEmpty()) ingredients += "$ingredient5 - $measure5\n"
            if (!ingredient6.isNullOrEmpty()) ingredients += "$ingredient6 - $measure6\n"
            if (!ingredient7.isNullOrEmpty()) ingredients += "$ingredient7 - $measure7\n"
            if (!ingredient8.isNullOrEmpty()) ingredients += "$ingredient8 - $measure8\n"
            if (!ingredient9.isNullOrEmpty()) ingredients += "$ingredient9 - $measure9\n"
            if (!ingredient10.isNullOrEmpty()) ingredients += "$ingredient10 - $measure10\n"
            if (!ingredient11.isNullOrEmpty()) ingredients += "$ingredient11 - $measure11\n"
            if (!ingredient12.isNullOrEmpty()) ingredients += "$ingredient12 - $measure12\n"
            if (!ingredient13.isNullOrEmpty()) ingredients += "$ingredient13 - $measure13\n"
            if (!ingredient14.isNullOrEmpty()) ingredients += "$ingredient14 - $measure14\n"
            if (!ingredient15.isNullOrEmpty()) ingredients += "$ingredient15 - $measure15\n"
            if (!ingredient16.isNullOrEmpty()) ingredients += "$ingredient16 - $measure16\n"
            if (!ingredient17.isNullOrEmpty()) ingredients += "$ingredient17 - $measure17\n"
            if (!ingredient18.isNullOrEmpty()) ingredients += "$ingredient18 - $measure18\n"
            if (!ingredient19.isNullOrEmpty()) ingredients += "$ingredient19 - $measure19\n"
            if (!ingredient20.isNullOrEmpty()) ingredients += "$ingredient20 - $measure20\n"
        }
        return ingredients.trimEnd('\n')
    }

}