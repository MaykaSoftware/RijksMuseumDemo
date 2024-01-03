package com.feature.home.ui.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.core.feature_api.FeatureApi

interface HomeApi : FeatureApi {

}

class HomeApiImpl : HomeApi {
    override fun registerGraph(
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder,
        modifier: Modifier,
        onTitleChanged: (String) -> Unit
    ) {
        InternalHomeFeatureApi.registerGraph(navController, navGraphBuilder, modifier, onTitleChanged)
    }
}