package com.feature.authentication.data.di

import com.feature.authentication.data.datasource.AuthCacheDataSource
import com.feature.authentication.data.datasource.AuthRemoteDataSource
import com.feature.authentication.data.repository.AuthRepository
import com.feature.authentication.data.repository.AuthRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataLayerModule {

    @Provides
    @Singleton
    fun provideAuthRepository(
        authRemoteDataSource: AuthRemoteDataSource,
        authCacheDataSource: AuthCacheDataSource
    ): AuthRepository {
        return AuthRepositoryImpl(
            authRemoteDataSource,
            authCacheDataSource
        )
    }
}