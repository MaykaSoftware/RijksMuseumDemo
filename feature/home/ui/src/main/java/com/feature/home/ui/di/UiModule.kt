package com.feature.home.ui.di

import com.feature.home.ui.navigation.HomeApi
import com.feature.home.ui.navigation.HomeApiImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object UiModule {

    @Provides
    fun provideSettingsApi(): HomeApi {
        return HomeApiImpl()
    }
}