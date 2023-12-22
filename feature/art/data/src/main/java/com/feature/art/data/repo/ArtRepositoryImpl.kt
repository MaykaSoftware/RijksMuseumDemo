package com.feature.art.data.repo


import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.map
import com.core.cache.entity.art.ArtObjectEntity
import com.feature.art.data.mapper.toArtObject
import com.feature.art.domain.model.ArtObject
import com.feature.art.domain.repo.ArtRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ArtRepositoryImpl @Inject constructor(
    pager: Pager<Int, ArtObjectEntity>
) : ArtRepository {
    override val artObjectData: Flow<PagingData<ArtObject>> =
        pager.flow.map {
            it.map { artObjectEntity ->
                artObjectEntity.toArtObject()
            }
        }.catch {
            throw it
        }
}