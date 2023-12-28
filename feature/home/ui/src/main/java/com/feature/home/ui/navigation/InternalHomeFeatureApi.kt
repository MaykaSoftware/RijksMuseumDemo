package com.feature.home.ui.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.core.common.navigation_constants.HomeFeature
import com.core.feature_api.FeatureApi
import com.feature.home.ui.screen.HomeScreen
import com.feature.home.ui.screen.HomeViewModel

internal object InternalHomeFeatureApi : FeatureApi {
    override fun registerGraph(
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder,
        modifier: Modifier
    ) {
        navGraphBuilder.navigation(
            startDestination = HomeFeature.homeScreenRoute,
            route = HomeFeature.nestedHomeRoute
        ) {
            composable(HomeFeature.homeScreenRoute) {
                val viewModel: HomeViewModel = hiltViewModel()
                val state by viewModel.state.collectAsState()
                HomeScreen(state)
            }
        }
    }
}