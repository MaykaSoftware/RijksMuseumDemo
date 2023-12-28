package com.farid.rijksmuseumdemo.di

import com.farid.rijksmuseumdemo.navigation.NavigationProvider
import com.feature.art.ui.navigation.ArtApi
import com.feature.home.ui.navigation.HomeApi
import com.feature.settings.ui.navigation.SettingsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideNavigationProvider(homeApi: HomeApi, artApi: ArtApi, settingsApi: SettingsApi): NavigationProvider {
        return NavigationProvider(homeApi, artApi, settingsApi)
    }
}