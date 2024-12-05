package com.coding.exercise.receipesearch.domain.usecase

import com.coding.exercise.receipesearch.common.UiState
import com.coding.exercise.receipesearch.common.Utils
import com.coding.exercise.receipesearch.domain.mapper.RecipeMapper
import com.coding.exercise.receipesearch.domain.model.Recipe
import com.coding.exercise.receipesearch.domain.repository.RecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SearchRecipeUseCase @Inject constructor(private val repository: RecipeRepository) {
    suspend fun  invoke(q: String): Flow<UiState<List<Recipe>>>  {
        return flow {
        try {
            emit(UiState.Loading())
            val data = withContext(Dispatchers.IO) {
                repository.searchRecipes(q)  // IO operation
            }
            val domainData =
                if (data.meals != null) data.meals!!.map { it -> RecipeMapper.toDomainMeal(it) } else emptyList()
            emit(UiState.Success(data = domainData))
        } catch (e: HttpException) {
            emit(UiState.Error(message = e.localizedMessage ?: "An Unknown error occurred"))
        } catch (e: IOException) {
            emit(UiState.NoNetwork())
        } catch (e: Exception) { }
        }.flowOn(Dispatchers.IO)
    }



}