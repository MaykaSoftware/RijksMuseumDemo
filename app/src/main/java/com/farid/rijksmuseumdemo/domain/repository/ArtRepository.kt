package com.farid.rijksmuseumdemo.domain.repository

import androidx.paging.PagingData
import com.farid.rijksmuseumdemo.domain.model.detail.ArtObjectDetail
import com.farid.rijksmuseumdemo.domain.model.home.ArtObject
import kotlinx.coroutines.flow.Flow

interface ArtRepository {
    val artObjectData: Flow<PagingData<ArtObject>>
    suspend fun getArtObjectFlow(objectNumber: String): Flow<ArtObjectDetail>

    suspend fun getArtObject(objectNumber: String): ArtObjectDetail
}