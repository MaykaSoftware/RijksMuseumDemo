package com.feature.onboarding.ui

sealed class OnBoardingEvent {
    data object SaveAppEntry: OnBoardingEvent()
}