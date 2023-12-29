package com.farid.rijksmuseumdemo.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.core.common.navigation_constants.HomeFeature

@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    navigationProvider: NavigationProvider
) {
    NavHost(navController = navController, startDestination = HomeFeature.nestedHomeRoute) {
        navigationProvider.homeApi.registerGraph(
            navController, this, modifier
        )
        navigationProvider.artApi.registerGraph(
            navController, this, modifier
        )

        navigationProvider.settingsApi.registerGraph(
            navController, this, modifier
        )
    }
}