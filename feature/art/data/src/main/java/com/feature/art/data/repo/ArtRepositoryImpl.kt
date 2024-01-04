package com.feature.art.data.repo


import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.map
import com.core.network.dataSource.ArtDataSource
import com.feature.art.data.dataSource.local.ArtObjectDaoImpl
import com.feature.common.domain.entity.art.ArtObjectEntity
import com.feature.common.domain.mapper.toArtDetailEntity
import com.feature.common.domain.mapper.toArtObject
import com.feature.common.domain.mapper.toArtObjectDetail
import com.feature.common.domain.model.art.ArtObject
import com.feature.common.domain.model.art_detail.ArtObjectDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ArtRepositoryImpl @Inject constructor(
    pager: Pager<Int, ArtObjectEntity>,
    private val artDataSource: ArtDataSource,
    private val artObjectDaoImpl: ArtObjectDaoImpl
) : ArtRepository {
    override val artObjectData: Flow<PagingData<ArtObject>> =
        pager.flow.map {
            it.map { artObjectEntity ->
                artObjectEntity.toArtObject()
            }
        }.catch {
            throw it
        }

    /**
     * Using Arrow of Result Kotlin class here would be a better choice for handling requests
     * **/
    override suspend fun getArtObjectFlow(objectNumber: String): Flow<ArtObjectDetail> {
        val result = artDataSource.getMuseumObject("nl", objectNumber)
        artObjectDaoImpl.insertArtObject(result.toArtDetailEntity().artObject)
        return artObjectDaoImpl.getArtObjectFlowById(objectNumber).map {
            it.toArtObjectDetail()
        }
    }

    override suspend fun getArtObject(objectNumber: String): ArtObjectDetail {
        val resultDb = artObjectDaoImpl.getArtObjectById(objectNumber)
        return if (resultDb != null) {
            resultDb.toArtObjectDetail()
        } else {
            val result = artDataSource.getMuseumObject("nl", objectNumber)
            artObjectDaoImpl.insertArtObject(result.toArtDetailEntity().artObject)
            result.artObject.toArtObjectDetail()
        }
    }
}