package com.coding.exercise.receipesearch.domain.usecase

import com.coding.exercise.receipesearch.common.UiState
import com.coding.exercise.receipesearch.domain.mapper.RecipeMapper
import com.coding.exercise.receipesearch.domain.model.RecipeDetail
import com.coding.exercise.receipesearch.domain.repository.RecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
class GetRecipeDetailUseCase  @Inject constructor(private val repository: RecipeRepository) {
    suspend fun  invoke(id: String): Flow<UiState<List<RecipeDetail>>> {

    return flow {
         try {
             emit(UiState.Loading())
             val data = withContext(Dispatchers.IO) {
                 repository.getRecipeDetails(id)
             }
             val domainData =
                 if (!data.meals.isNullOrEmpty()) data.meals!!.map { it ->
                     RecipeMapper.toDomainRecipeDetail(
                         it
                     )
                 } else emptyList()
             emit(UiState.Success(data = domainData))
         } catch (e: HttpException) {
             emit(UiState.Error(message = e.localizedMessage ?: "An Unknown error occurred"))
         } catch (e: IOException) {
             emit(UiState.NoNetwork())
         } catch (e: Exception) {

         }
     }.flowOn(Dispatchers.IO)
    }

}