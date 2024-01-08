package com.feature.settings.ui.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.core.common.constants.TopBarConstants
import com.core.feature_api.FeatureApi

interface SettingsApi : FeatureApi {

}

class SettingsApiImpl : SettingsApi {
    override fun registerGraph(
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder,
        modifier: Modifier,
        onTitleChanged: (TopBarConstants, String) -> Unit
    ) {
        InternalSettingsFeatureApi.registerGraph(
            navController,
            navGraphBuilder,
            modifier,
            onTitleChanged
        )
    }
}