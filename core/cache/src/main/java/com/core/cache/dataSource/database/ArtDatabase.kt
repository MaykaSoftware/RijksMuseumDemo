package com.core.cache.dataSource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.core.cache.dataSource.dao.ArtObjectDao
import com.core.cache.dataSource.dao.RemoteKeysDao
import com.feature.common.domain.entity.art.ArtObjectEntity
import com.feature.common.domain.entity.art.RemoteKeysEntity
import com.feature.common.domain.entity.art_detail.ArtObjectDetailEntity

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