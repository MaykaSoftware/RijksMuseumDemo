package com.farid.rijksmuseumdemo.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.map
import com.farid.rijksmuseumdemo.data.local.dao.ArtObjectDao
import com.farid.rijksmuseumdemo.data.local.entity.home.ArtObjectEntity
import com.farid.rijksmuseumdemo.data.mapper.toArtDetail
import com.farid.rijksmuseumdemo.data.mapper.toArtObject
import com.farid.rijksmuseumdemo.data.mapper.toArtObjectDetail
import com.farid.rijksmuseumdemo.data.remote.ArtObjectApi
import com.farid.rijksmuseumdemo.domain.model.detail.ArtObjectDetail
import com.farid.rijksmuseumdemo.domain.model.home.ArtObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ArtRepositoryImpl @Inject constructor(
    pager: Pager<Int, ArtObjectEntity>,
    private val artObjectApi: ArtObjectApi,
    private val artObjectDao: ArtObjectDao
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
        val result = artObjectApi.getMuseumObject("nl", objectNumber)
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
            val result = artObjectApi.getMuseumObject("nl", objectNumber)
            artObjectDao.insertArtObject(result.toArtDetail().artObject)
            result.artObject.toArtObjectDetail()
        }
    }
}