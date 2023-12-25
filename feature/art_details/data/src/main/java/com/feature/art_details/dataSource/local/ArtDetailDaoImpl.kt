package com.feature.art_details.dataSource.local

import com.core.cache.dataSource.dao.ArtDetailDao
import com.core.cache.dataSource.database.ArtDatabase
import com.feature.common.domain.entity.art_detail.ArtObjectDetailEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ArtDetailDaoImpl @Inject constructor(private val database: ArtDatabase): ArtDetailDao {
    override fun getArtObjectFlowById(objectNumber: String): Flow<ArtObjectDetailEntity> {
        return database.artDetailDao().getArtObjectFlowById(objectNumber)
    }

    override suspend fun getArtObjectById(objectNumber: String): ArtObjectDetailEntity? {
        return database.artDetailDao().getArtObjectById(objectNumber)
    }

    override suspend fun insertArtObject(artObjectEntity: ArtObjectDetailEntity) {
        return database.artDetailDao().insertArtObject(artObjectEntity)
    }
}