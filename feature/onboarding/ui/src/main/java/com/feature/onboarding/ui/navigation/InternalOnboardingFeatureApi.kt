package com.feature.onboarding.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.composable
import com.core.common.constants.OnboardingFeature
import com.core.common.constants.TopBarConstants
import com.core.feature_api.FeatureApi
import com.feature.onboarding.ui.OnBoardingViewModel
import com.feature.onboarding.ui.screen.OnBoardingScreen

internal object InternalOnboardingFeatureApi : FeatureApi {
    override fun registerGraph(
        navController: androidx.navigation.NavHostController,
        navGraphBuilder: androidx.navigation.NavGraphBuilder,
        modifier: Modifier,
        onTitleChanged: (TopBarConstants, String) -> Unit
    ) {
        navGraphBuilder.composable(OnboardingFeature.onboardingScreenRoute) {
            val viewModel: OnBoardingViewModel = hiltViewModel()
            OnBoardingScreen(
                modifier = Modifier.fillMaxSize(),
                navController = navController,
                event = viewModel::onEvent
            )
        }
    }
}