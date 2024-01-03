package com.farid.rijksmuseumdemo.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.farid.rijksmuseumdemo.navigation.AppNavGraph
import com.farid.rijksmuseumdemo.navigation.NavigationProvider
import com.feature.bottombar.BottomTabs
import com.feature.bottombar.NavigationBottomBar
import com.feature.bottombar.currentRoute

@Composable
fun RijksMuseumDemoApp(
    navigationProvider: NavigationProvider,
    startDestination: String
) {
    val navController = rememberNavController()
    val tabs = remember { BottomTabs.entries.toTypedArray() }
    val shouldShowBottomNavigation = showBottomBar(currentRoute(navController))

    Scaffold(
        bottomBar = {
            if (shouldShowBottomNavigation) NavigationBottomBar(
                navController = navController,
                tabs = tabs
            )
        }
    ) { innerPaddingModifier ->
        AppNavGraph(
            navController = navController,
            navigationProvider = navigationProvider,
            modifier = Modifier.padding(innerPaddingModifier),
            startDestination = startDestination
        )
    }
}

fun showBottomBar(currentRoute: String?): Boolean {
    return when (currentRoute) {
        BottomTabs.HOME.route,
        BottomTabs.ART.route,
        BottomTabs.SETTINGS.route,
        -> true

        else -> false
    }
}