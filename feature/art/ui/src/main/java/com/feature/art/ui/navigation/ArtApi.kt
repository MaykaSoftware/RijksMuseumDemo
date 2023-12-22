package com.feature.art.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.core.feature_api.FeatureApi

interface ArtApi: FeatureApi {
}

class ArtApiImpl: ArtApi {
    override fun registerGraph(navController: NavHostController, navGraphBuilder: NavGraphBuilder) {
        InternalArtFeatureApi.registerGraph(navController, navGraphBuilder)
    }

}