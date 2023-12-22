package com.core.cache.di

import android.content.Context
import androidx.room.Room
import com.core.cache.dataSource.dao.ArtObjectDao
import com.core.cache.dataSource.dao.RemoteKeysDao
import com.core.cache.dataSource.database.ArtDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Singleton
    @Provides
    fun provideArtObjectDB(@ApplicationContext context: Context): ArtDatabase {
        return Room.databaseBuilder(
            context,
            ArtDatabase::class.java,
            ArtDatabase.DATABASE_NAME,
        )
            .build()
    }

    @Singleton
    @Provides
    fun provideArtObjectDao(appDatabase: ArtDatabase): ArtObjectDao =
        appDatabase.artObjectDao()


    @Singleton
    @Provides
    fun provideRemoteKeysDao(artObjectDatabase: ArtDatabase): RemoteKeysDao =
        artObjectDatabase.getRemoteKeysDao()

}