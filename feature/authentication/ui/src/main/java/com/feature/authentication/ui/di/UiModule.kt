package com.feature.authentication.ui.di

import com.feature.authentication.ui.navigation.AuthApi
import com.feature.authentication.ui.navigation.AuthApiImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object UiModule {

    @Provides
    fun provideAuthApi(): AuthApi {
        return AuthApiImpl()
    }
}