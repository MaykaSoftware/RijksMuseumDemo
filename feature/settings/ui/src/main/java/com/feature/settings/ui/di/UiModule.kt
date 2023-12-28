package com.feature.settings.ui.di

import com.feature.settings.ui.navigation.SettingsApi
import com.feature.settings.ui.navigation.SettingsApiImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object UiModule {

    @Provides
    fun provideSettingsApi(): SettingsApi {
        return SettingsApiImpl()
    }
}