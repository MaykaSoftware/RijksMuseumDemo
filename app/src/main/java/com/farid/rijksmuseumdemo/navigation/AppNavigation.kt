package com.farid.rijksmuseumdemo.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    navigationProvider: NavigationProvider,
    startDestination: String
) {
    NavHost(navController = navController, startDestination = startDestination) {
        navigationProvider.onboardingApi.registerGraph(
            navController, this, modifier
        )
        navigationProvider.authApi.registerGraph(
            navController, this, modifier
        )
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