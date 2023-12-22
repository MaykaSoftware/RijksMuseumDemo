package com.feature.art_details.domain.repo

import com.feature.art_details.domain.model.ArtObjectDetail
import kotlinx.coroutines.flow.Flow

interface ArtDetailsRepository {
    suspend fun getArtObjectFlow(objectNumber: String): Flow<ArtObjectDetail>

    suspend fun getArtObject(objectNumber: String): ArtObjectDetail
}