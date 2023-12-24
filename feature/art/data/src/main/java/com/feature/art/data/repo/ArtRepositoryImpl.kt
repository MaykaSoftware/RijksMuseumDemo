package com.feature.art.data.repo


import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.map
import com.feature.common.domain.entity.art.ArtObjectEntity
import com.feature.common.domain.mapper.toArtObject
import com.feature.common.domain.model.art.ArtObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ArtRepositoryImpl @Inject constructor(
    pager: Pager<Int, ArtObjectEntity>
) : ArtRepository {
    override val artObjectData: Flow<PagingData<ArtObject>> =
        pager.flow.map<PagingData<ArtObjectEntity>, PagingData<ArtObject>> {
            it.map { artObjectEntity ->
                artObjectEntity.toArtObject()
            }
        }.catch {
            throw it
        }
}