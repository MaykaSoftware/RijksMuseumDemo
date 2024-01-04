package com.core.feature_api

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.core.common.constants.TopBarConstants

interface FeatureApi {
    fun registerGraph(
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder,
        modifier: Modifier = Modifier,
        onTitleChanged: (TopBarConstants, String) -> Unit
    )
}