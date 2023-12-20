package com.farid.rijksmuseumdemo.di

import com.farid.rijksmuseumdemo.data.remote.ArtObjectApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {
    @Singleton
    @Provides
    fun provideArtObjectApi(
        retrofitBuilder: Retrofit.Builder,
    ): ArtObjectApi {
        return retrofitBuilder
            .build()
            .create(ArtObjectApi::class.java)
    }
}