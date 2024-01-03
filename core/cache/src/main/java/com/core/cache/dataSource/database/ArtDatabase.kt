package com.core.cache.dataSource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.core.cache.dataSource.dao.ArtDetailDao
import com.core.cache.dataSource.dao.ArtObjectDao
import com.core.cache.dataSource.dao.RemoteKeysDao
import com.core.cache.dataSource.dao.UserDao
import com.feature.common.domain.entity.art.ArtObjectEntity
import com.feature.common.domain.entity.art.RemoteKeysEntity
import com.feature.common.domain.entity.art_detail.ArtObjectDetailEntity
import com.feature.common.domain.entity.auth.UserEntity

@Database(
    entities = [ArtObjectEntity::class, RemoteKeysEntity::class, ArtObjectDetailEntity::class, UserEntity::class],
    version = 1
)
@TypeConverters(ArtTypeConverter::class, ArtDetailTypeConverter::class)
abstract class ArtDatabase : RoomDatabase() {

    abstract fun artObjectDao(): ArtObjectDao
    abstract fun getRemoteKeysDao(): RemoteKeysDao
    abstract fun artDetailDao(): ArtDetailDao
    abstract fun userDao(): UserDao

    companion object {
        const val DATABASE_NAME = "art_object_db"
    }
}