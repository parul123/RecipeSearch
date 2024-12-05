package com.coding.exercise.receipesearch.di

import com.coding.exercise.receipesearch.common.AppConstants
import com.coding.exercise.receipesearch.data.network.RecipeApi
import com.coding.exercise.receipesearch.data.repository.RecipeRepositoryImpl
import com.coding.exercise.receipesearch.domain.repository.RecipeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    @Singleton
    fun provideMealApi(): RecipeApi {
        return Retrofit.Builder().baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(RecipeApi::class.java)
    }

    @Provides
    fun provideMealRepository(recipeApi: RecipeApi): RecipeRepository {
        return RecipeRepositoryImpl(recipeApi)
    }
}