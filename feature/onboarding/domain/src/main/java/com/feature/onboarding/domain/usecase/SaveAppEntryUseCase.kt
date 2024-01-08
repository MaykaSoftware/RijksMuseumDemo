package com.feature.onboarding.domain.usecase

import com.feature.onboarding.data.OnBoardingRepository
import javax.inject.Inject

class SaveAppEntryUseCase @Inject constructor(
    private val onBoardingRepository: OnBoardingRepository
) {

    suspend operator fun invoke() {
        onBoardingRepository.saveAppEntry()
    }
}