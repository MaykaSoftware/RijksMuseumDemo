package com.feature.art.data.repo

import androidx.paging.PagingData
import com.feature.common.domain.model.art.ArtObject
import kotlinx.coroutines.flow.Flow

interface ArtRepository {
    val artObjectData: Flow<PagingData<ArtObject>>
}