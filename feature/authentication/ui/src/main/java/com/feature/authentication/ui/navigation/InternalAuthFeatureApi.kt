package com.feature.authentication.ui.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.core.common.constants.AuthenticationFeature
import com.core.common.constants.TopBarConstants
import com.core.feature_api.FeatureApi
import com.feature.authentication.ui.login.LoginScreen
import com.feature.authentication.ui.login.LoginViewModel
import com.feature.authentication.ui.register.RegisterScreen
import com.feature.authentication.ui.register.RegisterViewModel

internal object InternalAuthFeatureApi : FeatureApi {
    override fun registerGraph(
        navController: androidx.navigation.NavHostController,
        navGraphBuilder: androidx.navigation.NavGraphBuilder,
        modifier: Modifier,
        onTitleChanged: (TopBarConstants, String) -> Unit
    ) {
        navGraphBuilder.navigation(
            startDestination = AuthenticationFeature.loginScreenRoute,
            route = AuthenticationFeature.nestedAuthenticationRoute
        ) {
            composable(AuthenticationFeature.loginScreenRoute) {
                val viewModel: LoginViewModel = hiltViewModel()
                val state by viewModel.uiState.collectAsState()
                LoginScreen(
                    modifier, navController, state, viewModel::onEvent
                )
            }
            composable(AuthenticationFeature.registerScreenRoute) {
                val viewModel: RegisterViewModel = hiltViewModel()
                val state by viewModel.uiState.collectAsState()
                RegisterScreen(modifier, navController, state, viewModel::onEvent)
            }
        }
    }
}