package com.feature.onboarding.domain.usecase

import com.feature.authentication.data.repository.AuthRepository
import com.feature.onboarding.data.OnBoardingRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ReadAppEntryUseCase @Inject constructor(
    private val onBoardingRepository: OnBoardingRepository,
    private val authRepository: AuthRepository
) {

    @OptIn(ExperimentalCoroutinesApi::class)
    operator fun invoke(): Flow<StartNavigation> {

        return onBoardingRepository.readAppEntry().flatMapLatest { hasOnBoarded ->
            authRepository.readToken().map { token ->
                if (hasOnBoarded && token.isNotEmpty()) {
                    StartNavigation.MAIN
                } else if (hasOnBoarded && token.isEmpty()) {
                    StartNavigation.AUTHENTICATION
                } else {
                    StartNavigation.ONBOARDING
                }
            }
        }
    }
}

enum class StartNavigation {
    ONBOARDING,
    AUTHENTICATION,
    MAIN
}