package com.feature.art.domain.repo

import androidx.paging.PagingData
import com.feature.art.domain.model.ArtObject
import kotlinx.coroutines.flow.Flow

interface ArtRepository {
    val artObjectData: Flow<PagingData<ArtObject>>
}