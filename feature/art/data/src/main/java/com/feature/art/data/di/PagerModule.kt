package com.feature.art.data.di

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.core.cache.dataSource.database.ArtDatabase
import com.core.network.dataSource.ArtDataSource
import com.feature.art.data.repo.ArtObjectRemoteMediator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PagerModule {
    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun provideArtObjectPager(
        artObjectDatabase: ArtDatabase,
        artObjectApi: ArtDataSource
    ): Pager<Int, com.feature.common.domain.entity.art.ArtObjectEntity> {
        return Pager(
            config = PagingConfig(
                pageSize = 10
            ),
            remoteMediator = ArtObjectRemoteMediator(
                artDatabase = artObjectDatabase,
                artDataSource = artObjectApi
            ),
            pagingSourceFactory = {
                artObjectDatabase.artObjectDao().pagingSource()
            }
        )
    }
}