package com.farid.rijksmuseumdemo.domain.usecase

import androidx.paging.PagingData
import com.farid.rijksmuseumdemo.domain.model.home.ArtObject
import com.farid.rijksmuseumdemo.domain.repository.ArtRepository
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