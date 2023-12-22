package com.feature.art_details.ui.di

import com.feature.art_details.ui.navigation.ArtDetailApi
import com.feature.art_details.ui.navigation.ArtDetailApiImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object UiModule {

    @Provides
    fun provideArtDetailApi(): ArtDetailApi {
        return ArtDetailApiImpl()
    }
}