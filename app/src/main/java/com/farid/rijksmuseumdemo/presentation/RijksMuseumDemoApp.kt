package com.farid.rijksmuseumdemo.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.farid.rijksmuseumdemo.navigation.AppNavGraph
import com.farid.rijksmuseumdemo.navigation.NavigationProvider
import com.feature.bottombar.BottomTabs
import com.feature.bottombar.NavigationBottomBar
import com.feature.bottombar.currentRoute
import com.feature.bottombar.showBottomBar
import com.feature.topbar.ArtAppBar
import com.feature.topbar.TopBarData
import com.feature.topbar.calculateDynamicActions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RijksMuseumDemoApp(
    navigationProvider: NavigationProvider,
    startDestination: String
) {
    val navController = rememberNavController()
    val tabs = remember { BottomTabs.entries.toTypedArray() }
    val shouldShowBottomNavigation = showBottomBar(currentRoute(navController))
    var dynamicTitle by remember { mutableStateOf("") }
    var dynamicActions by remember { mutableStateOf<List<TopBarData>>(emptyList()) }
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        bottomBar = {
            if (shouldShowBottomNavigation) NavigationBottomBar(
                navController = navController,
                tabs = tabs
            )
        },
        topBar = {
            if(dynamicTitle.isNotEmpty()) {
                ArtAppBar(
                    title = dynamicTitle,
                    scrollBehavior,
                    canNavigateBack = navController.previousBackStackEntry != null,
                    navigateUp = { navController.navigateUp() },
                    actions = dynamicActions
                )
            }
        }
    ) { innerPaddingModifier ->
        Box(modifier = Modifier
            .padding(innerPaddingModifier)
            .fillMaxSize()) {
            AppNavGraph(
                navController = navController,
                navigationProvider = navigationProvider,
                modifier = Modifier,
                startDestination = startDestination,
                onTitleChanged = { id, title ->
                    dynamicTitle = title
                    dynamicActions = calculateDynamicActions(id)
                }
            )
        }
    }
}