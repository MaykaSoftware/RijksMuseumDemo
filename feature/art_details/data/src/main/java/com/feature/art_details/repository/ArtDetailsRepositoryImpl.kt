package com.feature.art_details.repository

import com.core.network.dataSource.ArtDataSource
import com.feature.art_details.dataSource.local.ArtDetailDaoImpl
import com.feature.common.domain.mapper.toArtDetailEntity
import com.feature.common.domain.mapper.toArtObjectDetail
import com.feature.common.domain.model.art_detail.ArtObjectDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ArtDetailsRepositoryImpl @Inject constructor(
    private val artDataSource: ArtDataSource,
    private val artDetailDaoImpl: ArtDetailDaoImpl
): ArtDetailsRepository {

    /**
     * Using Arrow of Result Kotlin class here would be a better choice for handling requests
     * **/
    override suspend fun getArtObjectFlow(objectNumber: String): Flow<ArtObjectDetail> {
        val result = artDataSource.getMuseumObject("nl", objectNumber)
        artDetailDaoImpl.insertArtObject(result.toArtDetailEntity().artObject)
        return artDetailDaoImpl.getArtObjectFlowById(objectNumber).map {
            it.toArtObjectDetail()
        }
    }

    override suspend fun getArtObject(objectNumber: String): ArtObjectDetail {
        val resultDb = artDetailDaoImpl.getArtObjectById(objectNumber)
        return if(resultDb != null){
            resultDb.toArtObjectDetail()
        } else {
            val result = artDataSource.getMuseumObject("nl", objectNumber)
            artDetailDaoImpl.insertArtObject(result.toArtDetailEntity().artObject)
            result.artObject.toArtObjectDetail()
        }
    }
}