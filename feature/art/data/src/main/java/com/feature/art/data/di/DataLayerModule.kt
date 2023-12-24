package com.feature.art.data.di

import androidx.paging.Pager
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
    @Provides
    @Singleton
    fun bindArtRepository(
        pager: Pager<Int, ArtObjectEntity>
    ): ArtRepository {
        return ArtRepositoryImpl(pager)
    }
}