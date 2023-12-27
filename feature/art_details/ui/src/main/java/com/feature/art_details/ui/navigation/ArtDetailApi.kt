package com.feature.art_details.ui.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.core.feature_api.FeatureApi

interface ArtDetailApi: FeatureApi {
}

class ArtDetailApiImpl: ArtDetailApi {
    override fun registerGraph(navController: NavHostController, navGraphBuilder: NavGraphBuilder, modifier: Modifier) {
        InternalArtDetailFeatureApi.registerGraph(navController, navGraphBuilder, modifier)
    }

}