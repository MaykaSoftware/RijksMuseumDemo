package com.feature.settings.ui.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.core.common.navigation_constants.SettingsFeature
import com.core.feature_api.FeatureApi
import com.feature.settings.ui.screen.SettingsScreen
import com.feature.settings.ui.screen.SettingsViewModel

internal object InternalSettingsFeatureApi : FeatureApi {
    override fun registerGraph(
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder,
        modifier: Modifier
    ) {
        navGraphBuilder.navigation(
            startDestination = SettingsFeature.settingsScreenRoute,
            route = SettingsFeature.nestedSettingsRoute
        ) {
            composable(SettingsFeature.settingsScreenRoute) {
                val viewModel: SettingsViewModel = hiltViewModel()
                val state by viewModel.state.collectAsState()
                SettingsScreen(state)
            }
        }
    }
}