package com.feature.art.ui.di

import com.feature.art.ui.navigation.ArtApi
import com.feature.art.ui.navigation.ArtApiImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object UiModule {

    @Provides
    fun provideArtApi(): ArtApi {
        return ArtApiImpl()
    }
}