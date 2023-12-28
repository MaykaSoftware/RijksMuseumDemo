package com.farid.rijksmuseumdemo.di

import com.farid.rijksmuseumdemo.navigation.NavigationProvider
import com.feature.art.ui.navigation.ArtApi
import com.feature.art_details.ui.navigation.ArtDetailApi
import com.feature.settings.ui.navigation.SettingsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideNavigationProvider(artApi: ArtApi, artDetailApi: ArtDetailApi, settingsApi: SettingsApi): NavigationProvider {
        return NavigationProvider(artApi, artDetailApi, settingsApi)
    }
}