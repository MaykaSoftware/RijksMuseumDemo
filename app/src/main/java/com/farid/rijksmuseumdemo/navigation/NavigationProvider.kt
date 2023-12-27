package com.farid.rijksmuseumdemo.navigation

import com.feature.art.ui.navigation.ArtApi
import com.feature.art_details.ui.navigation.ArtDetailApi

data class NavigationProvider(
    val artApi: ArtApi,
    val artDetailApi: ArtDetailApi
)