package com.feature.art_details.repo

import com.core.cache.dataSource.dao.ArtObjectDao
import com.core.network.dataSource.ArtDataSource
import com.feature.art_details.domain.model.ArtObjectDetail
import com.feature.art_details.domain.repo.ArtDetailsRepository
import com.feature.art_details.mapper.toArtDetail
import com.feature.art_details.mapper.toArtObjectDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ArtDetailsRepositoryImpl @Inject constructor(
    private val artDataSource: ArtDataSource,
    private val artObjectDao: ArtObjectDao
): ArtDetailsRepository {

    /**
     * Using Arrow of Result Kotlin class here would be a better choice for handling requests
     * **/
    override suspend fun getArtObjectFlow(objectNumber: String): Flow<ArtObjectDetail> {
        val result = artDataSource.getMuseumObject("nl", objectNumber)
        artObjectDao.insertArtObject(result.toArtDetail().artObject)
        return artObjectDao.getArtObjectFlowById(objectNumber).map {
            it.toArtObjectDetail()
        }
    }

    override suspend fun getArtObject(objectNumber: String): ArtObjectDetail {
        val resultDb = artObjectDao.getArtObjectById(objectNumber)
        return if(resultDb != null){
            resultDb.toArtObjectDetail()
        } else {
            val result = artDataSource.getMuseumObject("nl", objectNumber)
            artObjectDao.insertArtObject(result.toArtDetail().artObject)
            result.artObject.toArtObjectDetail()
        }
    }
}