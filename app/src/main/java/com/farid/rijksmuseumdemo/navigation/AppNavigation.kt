package com.farid.rijksmuseumdemo.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.core.common.constants.TopBarConstants

@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    navigationProvider: NavigationProvider,
    startDestination: String,
    onTitleChanged: (TopBarConstants, String) -> Unit
) {
    NavHost(navController = navController, startDestination = startDestination) {
        navigationProvider.onboardingApi.registerGraph(
            navController, this, modifier, onTitleChanged
        )
        navigationProvider.authApi.registerGraph(
            navController, this, modifier, onTitleChanged
        )
        navigationProvider.homeApi.registerGraph(
            navController, this, modifier, onTitleChanged
        )
        navigationProvider.artApi.registerGraph(
            navController, this, modifier, onTitleChanged
        )
        navigationProvider.settingsApi.registerGraph(
            navController, this, modifier, onTitleChanged
        )
    }
}