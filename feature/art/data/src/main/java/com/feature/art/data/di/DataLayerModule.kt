package com.feature.art.data.di

import androidx.paging.Pager
import com.core.cache.dataSource.dao.ArtObjectDao
import com.core.cache.dataSource.dao.RemoteKeysDao
import com.core.network.dataSource.ArtDataSource
import com.feature.art.data.dataSource.local.ArtObjectDaoImpl
import com.feature.art.data.dataSource.local.RemoteKeysDaoImpl
import com.feature.art.data.repo.ArtRepository
import com.feature.art.data.repo.ArtRepositoryImpl
import com.feature.common.domain.entity.art.ArtObjectEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataLayerModule {

    @Singleton
    @Provides
    fun provideArtObjectDao(artObjectDaoImpl: ArtObjectDaoImpl): ArtObjectDao = artObjectDaoImpl

    @Singleton
    @Provides
    fun provideRemoteKeysDao(remoteKeysDaoImpl: RemoteKeysDaoImpl): RemoteKeysDao =
        remoteKeysDaoImpl

    @Provides
    @Singleton
    fun bindArtRepository(
        pager: Pager<Int, ArtObjectEntity>,
        artDataSource: ArtDataSource,
        artObjectDaoImpl: ArtObjectDaoImpl
    ): ArtRepository {
        return ArtRepositoryImpl(pager, artDataSource, artObjectDaoImpl)
    }
}