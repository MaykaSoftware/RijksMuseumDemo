package com.core.cache.dataSource.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RemoteKeysDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<com.feature.common.domain.entity.art.RemoteKeysEntity>)

    @Query("Select * From remote_key Where art_object_id = :id")
    suspend fun getRemoteKeyByArtObjectID(id: String): com.feature.common.domain.entity.art.RemoteKeysEntity?

    @Query("Delete From remote_key")
    suspend fun clearRemoteKeys()

    @Query("Select created_at From remote_key Order By created_at DESC LIMIT 1")
    suspend fun getCreationTime(): Long?
}