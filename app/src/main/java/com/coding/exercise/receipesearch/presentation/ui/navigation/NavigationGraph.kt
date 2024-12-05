package com.coding.exercise.receipesearch.presentation.ui.navigation

import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.coding.exercise.receipesearch.presentation.ui.screens.RecipeDetailScreen
import com.coding.exercise.receipesearch.presentation.ui.screens.RecipeSearchScreen
import com.coding.exercise.receipesearch.presentation.ui.viewmodels.RecipeSearchViewModel

object NavigationGraph {
    const val SEARCH_SCREEN = "recipe_search"
    const val RECIPE_DETAIL_SCREEN = "detail/{itemId}"

    fun NavGraphBuilder.setupNavGraph(navController: NavController) {

        composable(SEARCH_SCREEN) {
            RecipeSearchScreen(navController)
        }

        // Detail Screen route
        composable(RECIPE_DETAIL_SCREEN) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getString("itemId")?.toInt() ?: 0
            RecipeDetailScreen(navController, itemId.toString())
        }
    }

    @Composable
    fun SetupNavHost() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = SEARCH_SCREEN) {
            setupNavGraph(navController)
        }
    }
}