package com.farid.rijksmuseumdemo.navigation

import com.feature.art.ui.navigation.ArtApi
import com.feature.authentication.ui.navigation.AuthApi
import com.feature.home.ui.navigation.HomeApi
import com.feature.onboarding.ui.navigation.OnboardingApi
import com.feature.settings.ui.navigation.SettingsApi

data class NavigationProvider(
    val homeApi: HomeApi,
    val artApi: ArtApi,
    val settingsApi: SettingsApi,
    val authApi: AuthApi,
    val onboardingApi: OnboardingApi
)