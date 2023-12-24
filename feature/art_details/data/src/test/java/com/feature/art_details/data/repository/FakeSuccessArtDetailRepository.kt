package com.feature.art_details.data.repository

import com.feature.art_details.repository.ArtDetailsRepository
import com.feature.common.domain.model.art_detail.ArtObjectDetail
import com.feature.common.domain.model.art_detail.WebImageDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeSuccessArtDetailRepository: ArtDetailsRepository {

    private val artObject = ArtObjectDetail(
        "",
        true,
        "",
        "",
        "",
        "",
        true,
        "",
        "",
        WebImageDetail(
            "",
            120,
            20,
            20,
            "",
            0
        )

    )
    override suspend fun getArtObjectFlow(objectNumber: String): Flow<ArtObjectDetail> = flow {
        if(objectNumber.trim().isNotEmpty()){
            emit(artObject)
        } else {
            //throw EmptyFieldException()
        }
    }

    override suspend fun getArtObject(objectNumber: String): ArtObjectDetail {
        if(objectNumber.trim().isNotEmpty()){
            return artObject
        } else {
            throw EmptyFieldException()
        }
    }
}

class EmptyFieldException: Exception()