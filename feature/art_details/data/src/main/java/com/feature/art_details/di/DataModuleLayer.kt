package com.feature.art_details.di

import com.core.cache.dataSource.dao.ArtObjectDao
import com.core.network.dataSource.ArtDataSource
import com.feature.art_details.repository.ArtDetailsRepository
import com.feature.art_details.repository.ArtDetailsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DataModuleLayer {

    @Provides
    fun provideArtDetailRepo(artDataSource: ArtDataSource, artObjectDao: ArtObjectDao): ArtDetailsRepository {
        return ArtDetailsRepositoryImpl(artDataSource, artObjectDao)
    }
}