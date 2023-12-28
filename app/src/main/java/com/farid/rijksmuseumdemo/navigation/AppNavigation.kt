package com.farid.rijksmuseumdemo.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.core.common.navigation_constants.ArtFeature

@Composable
fun AppNavGraph(modifier: Modifier = Modifier, navController: NavHostController, navigationProvider: NavigationProvider){
    NavHost(navController = navController, startDestination = ArtFeature.nestedRoute) {
        navigationProvider.artApi.registerGraph(
            navController, this, modifier
        )

        navigationProvider.settingsApi.registerGraph(
            navController, this, modifier
        )
    }
}