package com.feature.bottombar

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.core.common.navigation_constants.ArtFeature
import com.core.common.navigation_constants.SettingsFeature
import java.util.Locale

enum class BottomTabs(
    val title: String,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector,
    val route: String
) {
    HOME("Home", Icons.Filled.Home, Icons.Outlined.Home, ArtFeature.artScreenRoute),
    SETTINGS("Settings", Icons.Filled.Settings, Icons.Outlined.Settings,SettingsFeature.settingsScreenRoute)
}

@Composable
fun currentRoute(navController: NavController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}

@Composable
fun NavigationBottomBar(
    navController: NavController,
    tabs: Array<BottomTabs>
) {
        val currentRoute = currentRoute(navController = navController)
        NavigationBar {
            tabs.forEach { tab ->
                NavigationBarItem(
                    icon = { Icon( imageVector = if (tab.route == currentRoute) {
                        tab.selectedIcon
                    } else {
                        tab.unSelectedIcon
                    }, contentDescription = tab.title) },
                    label = { Text(tab.title.uppercase(Locale.getDefault())) },
                    selected = currentRoute == tab.route,
                    onClick = {
                        if (tab.route != currentRoute) {
                                navController.navigate(tab.route)
                            }
                    },
                    alwaysShowLabel = false,
                    modifier = Modifier.navigationBarsPadding()
                )
            }
        }
}