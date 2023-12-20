package com.farid.rijksmuseumdemo.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.farid.rijksmuseumdemo.data.local.dao.ArtObjectDao
import com.farid.rijksmuseumdemo.data.local.dao.RemoteKeysDao
import com.farid.rijksmuseumdemo.data.local.entity.detail.ArtObjectDetailEntity
import com.farid.rijksmuseumdemo.data.local.entity.home.ArtObjectEntity
import com.farid.rijksmuseumdemo.data.local.entity.home.RemoteKeysEntity

@Database(
    entities = [ArtObjectEntity::class, RemoteKeysEntity::class, ArtObjectDetailEntity::class],
    version = 1
)
@TypeConverters(ArtTypeConverter::class, ArtDetailTypeConverter::class)
abstract class ArtDatabase : RoomDatabase() {

    abstract fun artObjectDao(): ArtObjectDao
    abstract fun getRemoteKeysDao(): RemoteKeysDao

    companion object {
        const val DATABASE_NAME = "art_object_db"
    }
}