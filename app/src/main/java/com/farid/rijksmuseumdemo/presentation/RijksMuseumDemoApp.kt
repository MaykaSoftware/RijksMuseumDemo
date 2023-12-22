package com.farid.rijksmuseumdemo.presentation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.farid.rijksmuseumdemo.navigation.AppNavGraph
import com.farid.rijksmuseumdemo.navigation.NavigationProvider

sealed class Route(
    val route: String
){
    data object ArtScreen: Route(route = "Art Screen")
    data object ArtDetailScreen: Route(route = "Art Detail")
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RijksMuseumDemoApp(
    navigationProvider: NavigationProvider
) {
    val navController = rememberNavController()
    AppNavGraph(navController = navController, navigationProvider = navigationProvider)
}