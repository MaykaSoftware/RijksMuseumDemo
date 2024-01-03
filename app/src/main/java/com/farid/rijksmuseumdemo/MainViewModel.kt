package com.farid.rijksmuseumdemo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.navigation_constants.AuthenticationFeature
import com.core.common.navigation_constants.HomeFeature
import com.core.common.navigation_constants.OnboardingFeature
import com.feature.onboarding.domain.usecase.ReadAppEntryUseCase
import com.feature.onboarding.domain.usecase.StartNavigation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    readAppEntryUseCase: ReadAppEntryUseCase
) : ViewModel() {
    var splashCondition by mutableStateOf(true)
        private set

    var startDestination by mutableStateOf(State())
        private set

    init {
        readAppEntryUseCase.invoke().onEach { startNavigation ->
            startDestination = when (startNavigation) {
                StartNavigation.ONBOARDING -> {
                    State(OnboardingFeature.onboardingScreenRoute)
                }
                StartNavigation.AUTHENTICATION -> {
                    State(AuthenticationFeature.nestedAuthenticationRoute)
                }
                StartNavigation.MAIN -> {
                    State(HomeFeature.nestedHomeRoute)
                }
            }
            splashCondition = false
        }.launchIn(viewModelScope)
    }
}

data class State(
    val startDestination: String = ""
)