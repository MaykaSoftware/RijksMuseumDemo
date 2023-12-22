package com.farid.rijksmuseumdemo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.core.common.navigation_constants.ArtFeature

@Composable
fun AppNavGraph(navController: NavHostController, navigationProvider: NavigationProvider){
    NavHost(navController = navController, startDestination = ArtFeature.nestedRoute) {
        navigationProvider.artApi.registerGraph(
            navController, this
        )

        navigationProvider.artDetailApi.registerGraph(
            navController, this
        )
    }

}