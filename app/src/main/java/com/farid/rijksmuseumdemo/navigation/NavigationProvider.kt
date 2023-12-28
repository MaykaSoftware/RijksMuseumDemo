package com.farid.rijksmuseumdemo.navigation

import com.feature.art.ui.navigation.ArtApi
import com.feature.home.ui.navigation.HomeApi
import com.feature.settings.ui.navigation.SettingsApi

data class NavigationProvider(
    val homeApi: HomeApi,
    val artApi: ArtApi,
    val settingsApi: SettingsApi
)