package com.feature.authentication.ui.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.core.common.constants.TopBarConstants
import com.core.feature_api.FeatureApi

interface AuthApi : FeatureApi {
}

class AuthApiImpl : AuthApi {
    override fun registerGraph(
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder,
        modifier: Modifier,
        onTitleChanged: (TopBarConstants, String) -> Unit
    ) {
        InternalAuthFeatureApi.registerGraph(navController, navGraphBuilder, modifier, onTitleChanged)
    }

}