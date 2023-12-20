package com.farid.rijksmuseumdemo.di

import com.farid.rijksmuseumdemo.data.local.dao.ArtObjectDao
import com.farid.rijksmuseumdemo.data.local.dao.RemoteKeysDao
import com.farid.rijksmuseumdemo.data.local.database.ArtDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Singleton
    @Provides
    fun provideArtObjectDao(appDatabase: ArtDatabase): ArtObjectDao =
        appDatabase.artObjectDao()


    @Singleton
    @Provides
    fun provideRemoteKeysDao(artObjectDatabase: ArtDatabase): RemoteKeysDao =
        artObjectDatabase.getRemoteKeysDao()

}