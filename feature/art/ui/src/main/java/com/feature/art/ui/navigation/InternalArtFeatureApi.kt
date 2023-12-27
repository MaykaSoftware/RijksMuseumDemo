package com.feature.art.ui.navigation

import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.paging.compose.collectAsLazyPagingItems
import com.core.common.navigation_constants.ArtFeature
import com.core.feature_api.FeatureApi
import com.feature.art.ui.screen.ArtScreen
import com.feature.art.ui.screen.ArtViewModel

internal object InternalArtFeatureApi : FeatureApi {
    override fun registerGraph(
        navController: androidx.navigation.NavHostController,
        navGraphBuilder: androidx.navigation.NavGraphBuilder,
        modifier: Modifier
    ) {
//        navGraphBuilder.composable(ArtFeature.artScreenRoute) {
//            val viewModel: ArtViewModel = hiltViewModel()
//            val artObjects = viewModel.artObjectFlow.collectAsLazyPagingItems()
//            ArtScreen(artObjects, navController, modifier)
//        }

        navGraphBuilder.navigation(
            startDestination = ArtFeature.artScreenRoute,
            route = ArtFeature.nestedRoute
        ) {
            composable(ArtFeature.artScreenRoute) {
                val viewModel: ArtViewModel = hiltViewModel()
                val artObjects = viewModel.artObjectFlow.collectAsLazyPagingItems()
                ArtScreen(artObjects, navController, modifier)
            }
        }
    }
}