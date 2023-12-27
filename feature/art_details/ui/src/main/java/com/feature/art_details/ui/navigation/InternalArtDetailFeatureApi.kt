package com.feature.art_details.ui.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.core.common.navigation_constants.ArtDetailFeature
import com.core.feature_api.FeatureApi
import com.feature.art_details.ui.screen.ArtDetailViewModel
import com.feature.art_details.ui.screen.ArtDetailsScreen

internal object InternalArtDetailFeatureApi : FeatureApi {
    override fun registerGraph(
        navController: androidx.navigation.NavHostController,
        navGraphBuilder: androidx.navigation.NavGraphBuilder,
        modifier: Modifier
    ) {
        navGraphBuilder.navigation(startDestination = ArtDetailFeature.artDetailScreenRoute, route = ArtDetailFeature.nestedDetailRoute){
            composable(ArtDetailFeature.artDetailScreenRoute){
                val viewModel: ArtDetailViewModel = hiltViewModel()
                val state by viewModel.state.collectAsState()
                ArtDetailsScreen(state, navController, modifier)
            }
        }
    }
}