package com.feature.onboarding.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OnBoardingModule {

    @Singleton
    @Provides
    fun provideOnBoardingRepository(
        dataStore: DataStore<Preferences>
    ): OnBoardingRepository {
        return OnBoardingRepositoryImpl(
            dataStore
        )
    }
}