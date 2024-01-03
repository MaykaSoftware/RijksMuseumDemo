package com.feature.onboarding.ui.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.core.feature_api.FeatureApi

interface OnboardingApi : FeatureApi {
}

class OnboardingApiImpl : OnboardingApi {
    override fun registerGraph(
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder,
        modifier: Modifier
    ) {
        InternalOnboardingFeatureApi.registerGraph(navController, navGraphBuilder, modifier)
    }

}