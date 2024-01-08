package com.feature.settings.ui.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.core.common.constants.SettingsFeature
import com.core.common.constants.TopBarConstants
import com.core.feature_api.FeatureApi
import com.feature.settings.ui.R
import com.feature.settings.ui.screen.SettingsScreen
import com.feature.settings.ui.screen.SettingsViewModel

internal object InternalSettingsFeatureApi : FeatureApi {
    override fun registerGraph(
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder,
        modifier: Modifier,
        onTitleChanged: (TopBarConstants, String) -> Unit
    ) {
        navGraphBuilder.navigation(
            startDestination = SettingsFeature.settingsScreenRoute,
            route = SettingsFeature.nestedSettingsRoute
        ) {
            composable(SettingsFeature.settingsScreenRoute) {
                val context = LocalContext.current
                onTitleChanged(
                    TopBarConstants.SETTINGS,
                    context.getString(R.string.title_topbar_setting)
                )
                val viewModel: SettingsViewModel = hiltViewModel()
                val state by viewModel.state.collectAsState()
                SettingsScreen(state)
            }
        }
    }
}