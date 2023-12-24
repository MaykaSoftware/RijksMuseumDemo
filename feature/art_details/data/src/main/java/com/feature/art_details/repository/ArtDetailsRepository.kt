package com.feature.art_details.repository

import com.feature.common.domain.model.art_detail.ArtObjectDetail
import kotlinx.coroutines.flow.Flow

interface ArtDetailsRepository {
    suspend fun getArtObjectFlow(objectNumber: String): Flow<ArtObjectDetail>

    suspend fun getArtObject(objectNumber: String): ArtObjectDetail
}