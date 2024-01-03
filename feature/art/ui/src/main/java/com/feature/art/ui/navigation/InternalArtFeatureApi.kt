package com.feature.art.ui.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.paging.compose.collectAsLazyPagingItems
import com.core.common.navigation_constants.ArtFeature
import com.core.feature_api.FeatureApi
import com.feature.art.ui.R
import com.feature.art.ui.screen.art.ArtScreen
import com.feature.art.ui.screen.art.ArtViewModel
import com.feature.art.ui.screen.art_detail.ArtDetailViewModel
import com.feature.art.ui.screen.art_detail.ArtDetailsScreen

internal object InternalArtFeatureApi : FeatureApi {
    override fun registerGraph(
        navController: androidx.navigation.NavHostController,
        navGraphBuilder: androidx.navigation.NavGraphBuilder,
        modifier: Modifier,
        onTitleChanged: (String) -> Unit
    ) {
        navGraphBuilder.navigation(
            startDestination = ArtFeature.artScreenRoute,
            route = ArtFeature.nestedRoute
        ) {
            composable(ArtFeature.artScreenRoute) {
                val context = LocalContext.current
                onTitleChanged(context.getString(R.string.title_topbar_art))
                val viewModel: ArtViewModel = hiltViewModel()
                val artObjects = viewModel.artObjectFlow.collectAsLazyPagingItems()
                ArtScreen(artObjects, navController, modifier)
            }
            composable(ArtFeature.artDetailScreenRoute) {
                val context = LocalContext.current
                onTitleChanged(context.getString(R.string.title_topbar_art_detail))
                val viewModel: ArtDetailViewModel = hiltViewModel()
                val state by viewModel.state.collectAsState()
                ArtDetailsScreen(state, navController, modifier)
            }
        }
    }
}