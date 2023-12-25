package com.feature.art.data.dataSource.local

import com.core.cache.dataSource.dao.RemoteKeysDao
import com.core.cache.dataSource.database.ArtDatabase
import com.feature.common.domain.entity.art.RemoteKeysEntity
import javax.inject.Inject

class RemoteKeysDaoImpl @Inject constructor(private val database: ArtDatabase): RemoteKeysDao {
    override suspend fun insertAll(remoteKey: List<RemoteKeysEntity>) {
        database.getRemoteKeysDao().insertAll(remoteKey)
    }

    override suspend fun getRemoteKeyByArtObjectID(id: String): RemoteKeysEntity? {
        return database.getRemoteKeysDao().getRemoteKeyByArtObjectID(id)
    }

    override suspend fun clearRemoteKeys() {
        database.getRemoteKeysDao().clearRemoteKeys()
    }

    override suspend fun getCreationTime(): Long? {
        return database.getRemoteKeysDao().getCreationTime()
    }
}