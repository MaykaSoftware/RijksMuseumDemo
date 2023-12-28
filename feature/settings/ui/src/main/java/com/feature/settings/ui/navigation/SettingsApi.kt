package com.feature.settings.ui.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.core.feature_api.FeatureApi

interface SettingsApi: FeatureApi {

}

class SettingsApiImpl: SettingsApi {
    override fun registerGraph(
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder,
        modifier: Modifier
    ) {
        InternalSettingsFeatureApi.registerGraph(navController, navGraphBuilder, modifier)
    }
}