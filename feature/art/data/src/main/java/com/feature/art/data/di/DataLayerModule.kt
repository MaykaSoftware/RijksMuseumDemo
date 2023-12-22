package com.feature.art.data.di

import androidx.paging.Pager
import com.core.cache.entity.art.ArtObjectEntity
import com.feature.art.data.repo.ArtRepositoryImpl
import com.feature.art.domain.repo.ArtRepository
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
    fun bindArtRepository(
        pager: Pager<Int, ArtObjectEntity>
    ): ArtRepository {
        return ArtRepositoryImpl(pager)
    }
}