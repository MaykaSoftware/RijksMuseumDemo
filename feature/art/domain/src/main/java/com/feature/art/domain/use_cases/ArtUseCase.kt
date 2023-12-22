package com.feature.art.domain.use_cases

import androidx.paging.PagingData
import com.feature.art.domain.model.ArtObject
import com.feature.art.domain.repo.ArtRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import javax.inject.Inject

class ArtUseCase @Inject constructor(
    private val artRepository: ArtRepository
) {
    operator fun invoke(): Flow<PagingData<ArtObject>> =
        artRepository.artObjectData.catch {
            emptyList<ArtObject>()
        }
}