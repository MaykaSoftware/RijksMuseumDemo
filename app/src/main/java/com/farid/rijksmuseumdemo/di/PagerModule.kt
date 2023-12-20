package com.farid.rijksmuseumdemo.di

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.farid.rijksmuseumdemo.data.local.ArtObjectRemoteMediator
import com.farid.rijksmuseumdemo.data.local.database.ArtDatabase
import com.farid.rijksmuseumdemo.data.local.entity.home.ArtObjectEntity
import com.farid.rijksmuseumdemo.data.remote.ArtObjectApi
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
        artObjectApi: ArtObjectApi
    ): Pager<Int, ArtObjectEntity> {
        return Pager(
            config = PagingConfig(
                pageSize = 10
            ),
            remoteMediator = ArtObjectRemoteMediator(
                artDatabase = artObjectDatabase,
                artObjectApi = artObjectApi
            ),
            pagingSourceFactory = {
                artObjectDatabase.artObjectDao().pagingSource()
            }
        )
    }
}