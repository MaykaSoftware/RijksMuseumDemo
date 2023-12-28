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
    navigationProvider: NavigationProvider
) {
    val navController = rememberNavController()
    val tabs = remember { BottomTabs.entries.toTypedArray() }

    val shouldShowBottomNavigation = when (currentRoute(navController)) {
        BottomTabs.HOME.route,
        BottomTabs.SETTINGS.route,
        -> true
        else -> false
    }

    Scaffold(
        bottomBar = { if (shouldShowBottomNavigation) NavigationBottomBar(navController = navController, tabs = tabs) }
    ) { innerPaddingModifier ->
        AppNavGraph(
            navController = navController,
            navigationProvider = navigationProvider,
            modifier = Modifier.padding(innerPaddingModifier)
        )
    }
}