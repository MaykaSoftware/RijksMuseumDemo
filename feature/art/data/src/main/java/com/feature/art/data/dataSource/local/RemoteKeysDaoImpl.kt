package com.feature.art.data.dataSource.local

import com.core.cache.dataSource.dao.RemoteKeysDao
import com.core.cache.dataSource.database.ArtDatabase
import com.feature.common.domain.entity.art.RemoteKeysEntity
import javax.inject.Inject

class RemoteKeysDaoImpl @Inject constructor(private val artDatabase: ArtDatabase): RemoteKeysDao {
    override suspend fun insertAll(remoteKey: List<RemoteKeysEntity>) {
        TODO("Not yet implemented")
    }

    override suspend fun getRemoteKeyByArtObjectID(id: String): RemoteKeysEntity? {
        TODO("Not yet implemented")
    }

    override suspend fun clearRemoteKeys() {
        TODO("Not yet implemented")
    }

    override suspend fun getCreationTime(): Long? {
        TODO("Not yet implemented")
    }
}