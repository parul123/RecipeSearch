package com.coding.exercise.receipesearch.presentation.ui.screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.coding.exercise.receipesearch.domain.model.Recipe
import com.coding.exercise.receipesearch.presentation.ui.state.RecipeSearchIntent
import com.coding.exercise.receipesearch.presentation.ui.state.RecipeViewState
import com.coding.exercise.receipesearch.presentation.ui.viewmodels.RecipeSearchViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import com.coding.exercise.receipesearch.R
import com.coding.exercise.receipesearch.presentation.ui.base.component.NoNetworkErrorComponent
import com.coding.exercise.receipesearch.presentation.ui.base.component.TopBar

@Composable
fun RecipeSearchScreen(
    navController: NavController
) {
    val recipeViewModel: RecipeSearchViewModel = hiltViewModel()
    var query by remember { mutableStateOf("") }
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    Scaffold( topBar = {
        TopBar(navController, LocalContext.current.getString(R.string.search_screen_header_title),  false)
    },modifier = Modifier.fillMaxSize()) { innerPadding ->
        Surface(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(

                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    OutlinedTextField(
                        modifier = Modifier.weight(1f),
                        value = query,
                        onValueChange = {
                            query = it
                        },
                        label = {
                            Text(text = context.getString(R.string.search_for_a_recipe))
                        }
                    )
                    IconButton(onClick = {
                        if (query.isEmpty()) {
                            Toast.makeText(context, context.getString(R.string.empty_query_string_error), Toast.LENGTH_SHORT).show()
                        } else {
                            recipeViewModel.processIntent(RecipeSearchIntent.SearchRecipes(query))
                        }

                        keyboardController?.hide()
                    }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = context.getString(R.string.search_for_a_recipe)
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
                val state by recipeViewModel.state

                when (state) {
                    is RecipeViewState.Init -> {
                    }
                    is RecipeViewState.Loading -> {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            CircularProgressIndicator()
                        }

                    }

                    is RecipeViewState.Success -> {
                        val recipes = (state as RecipeViewState.Success).recipes
                        if (recipes.isEmpty()) {
                            Text(text = context.getString(R.string.no_result_found))
                        } else {
                            MealGrid(recipes, navController)
                        }
                    }
                    is RecipeViewState.Error -> {
                        val message = (state as RecipeViewState.Error).message
                        Toast.makeText(context , message, Toast.LENGTH_SHORT).show()
                    }
                    is RecipeViewState.NetworkError -> {
                        NoNetworkErrorComponent(context = context)
                    }

                }
        }
    }

    }

}

@Composable
fun MealGrid(meals: List<Recipe>, navController:NavController) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(meals, key = { it.id }) { meal ->
            MealCard(meal = meal, navController)
        }
    }
}

@Composable
fun MealCard(meal: Recipe, navController:NavController) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                Navigation.navigateToDetail(navController, meal.id)
            }
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(meal.image)
                    .crossfade(true)
                    .build(),
                contentDescription = meal.name,
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = meal.name,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}

