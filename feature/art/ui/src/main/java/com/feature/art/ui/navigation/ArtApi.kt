package com.feature.art.ui.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.core.common.constants.TopBarConstants
import com.core.feature_api.FeatureApi

interface ArtApi : FeatureApi {
}

class ArtApiImpl : ArtApi {
    override fun registerGraph(
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder,
        modifier: Modifier,
        onTitleChanged: (TopBarConstants, String) -> Unit
    ) {
        InternalArtFeatureApi.registerGraph(
            navController,
            navGraphBuilder,
            modifier,
            onTitleChanged
        )
    }

}