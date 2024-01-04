package com.feature.onboarding.ui.di

import com.feature.onboarding.ui.navigation.OnboardingApi
import com.feature.onboarding.ui.navigation.OnboardingApiImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object UiModule {

    @Provides
    fun provideOnboardingApi(): OnboardingApi {
        return OnboardingApiImpl()
    }
}