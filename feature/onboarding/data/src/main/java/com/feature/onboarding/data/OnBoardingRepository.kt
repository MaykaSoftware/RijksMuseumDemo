package com.feature.onboarding.data

import kotlinx.coroutines.flow.Flow

interface OnBoardingRepository {
    suspend fun saveAppEntry()

    fun readAppEntry(): Flow<Boolean>
}