package com.feature.art.data.repo

import androidx.paging.PagingData
import com.feature.common.domain.model.art.ArtObject
import com.feature.common.domain.model.art_detail.ArtObjectDetail
import kotlinx.coroutines.flow.Flow

interface ArtRepository {
    val artObjectData: Flow<PagingData<ArtObject>>
    suspend fun getArtObjectFlow(objectNumber: String): Flow<ArtObjectDetail>

    suspend fun getArtObject(objectNumber: String): ArtObjectDetail
}