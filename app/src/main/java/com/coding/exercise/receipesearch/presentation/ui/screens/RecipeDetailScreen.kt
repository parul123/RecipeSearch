package com.coding.exercise.receipesearch.presentation.ui.screens

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.coding.exercise.receipesearch.domain.model.RecipeDetail
import com.coding.exercise.receipesearch.presentation.ui.Mapper
import com.coding.exercise.receipesearch.presentation.ui.base.component.LoadingComponent
import com.coding.exercise.receipesearch.presentation.ui.base.component.NoNetworkErrorComponent
import com.coding.exercise.receipesearch.presentation.ui.base.component.TopBar
import com.coding.exercise.receipesearch.presentation.ui.state.RecipeDetailIntent
import com.coding.exercise.receipesearch.presentation.ui.state.RecipeDetailState
import com.coding.exercise.receipesearch.presentation.ui.viewmodels.RecipeDetailScreenModel


@Composable
fun RecipeDetailScreen(navController: NavController, mealId: String) {
    val recipeViewModel: RecipeDetailScreenModel = hiltViewModel()
    val state by recipeViewModel.state
    var title by rememberSaveable {
        mutableStateOf("")
    }
    Scaffold( topBar = {
        TopBar(navController, title,  true)
    },modifier = Modifier.fillMaxSize()) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = MaterialTheme.colorScheme.background
        ) {
            when(state) {
                is RecipeDetailState.Loading -> LoadingComponent()
                is RecipeDetailState.Success -> {
                    val recipe = (state as RecipeDetailState.Success).recipes
                    title = recipe.name
                    RecipeListItem(recipe)
                }
                is RecipeDetailState.Error -> {
                    val message = (state as RecipeDetailState.Error).message
                    Toast.makeText(LocalContext.current , message, Toast.LENGTH_SHORT).show()
                }
                is RecipeDetailState.NetworkError -> {
                    NoNetworkErrorComponent(context = LocalContext.current)
                }

            }

            LaunchedEffect(Unit) {
                recipeViewModel.processIntent(RecipeDetailIntent.getRecipeDetail(mealId))
            }
        }
    }


}

@Composable
fun RecipeListItem(meal: RecipeDetail) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(4.dp),
        border = BorderStroke(1.dp, Color.LightGray),
        colors = CardDefaults.cardColors(
            Color.White
        )
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            if (!meal.image.isNullOrEmpty()) {
                AsyncImage(
                    model = meal.image,
                    contentDescription = "thumbnail",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(
                            RoundedCornerShape(8.dp)
                        )
                )
            }
            Spacer(modifier = Modifier.padding(4.dp))
            Text(
                text = meal.name ?: "",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Text(
                text = "Ingredients",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = Mapper.mapDomainToDetailView(meal)
            )
            Spacer(modifier = Modifier.padding(8.dp))

            AnimatedVisibility(visible = expanded) {
                Column {
                    Text(
                        text = "Instructions",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = meal.instructions ?: ""
                    )
                }
            }
        }
    }
}

