package com.feature.art_details.di

import com.core.cache.dataSource.dao.ArtDetailDao
import com.core.cache.dataSource.dao.ArtObjectDao
import com.core.network.dataSource.ArtDataSource
import com.feature.art_details.dataSource.local.ArtDetailDaoImpl
import com.feature.art_details.repository.ArtDetailsRepository
import com.feature.art_details.repository.ArtDetailsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataModuleLayer {

    @Singleton
    @Provides
    fun provideArtObjectDao(artDetailDaoImpl: ArtDetailDaoImpl): ArtDetailDao = artDetailDaoImpl


    @Provides
    fun provideArtDetailRepo(artDataSource: ArtDataSource, artDetailDaoImpl: ArtDetailDaoImpl): ArtDetailsRepository {
        return ArtDetailsRepositoryImpl(artDataSource, artDetailDaoImpl)
    }
}