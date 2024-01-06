package com.feature.art.ui.screen.art

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.insertSeparators
import androidx.paging.map
import com.core.common.model.art.ArtObject
import com.feature.art.domain.use_cases.ArtUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class ArtViewModel @Inject constructor(
    artUseCase: ArtUseCase
) : ViewModel() {

    val artObjectFlow = artUseCase.invoke().distinctUntilChanged().map {
        mapToUiModels(it)
    }.cachedIn(viewModelScope).map {
        insertSeparators(it)
    }

    private fun mapToUiModels(pagingData: PagingData<ArtObject>): PagingData<ListArtObjectUiState.ArtObjectItem> =
        pagingData.map { artObject ->
            ListArtObjectUiState.ArtObjectItem(artObject)
        }

    private fun insertSeparators(artObjectData: PagingData<ListArtObjectUiState.ArtObjectItem>): PagingData<ListArtObjectUiState> =
        artObjectData.insertSeparators { before: ListArtObjectUiState.ArtObjectItem?,
                                         after: ListArtObjectUiState.ArtObjectItem? ->
            createHeaderSeparatorsIfNeedTo(after, before)
        }

    private fun createHeaderSeparatorsIfNeedTo(
        after: ListArtObjectUiState.ArtObjectItem?,
        before: ListArtObjectUiState.ArtObjectItem?
    ): ListArtObjectUiState.ArtHeaderItem? {

        if (after == null) {
            return null
        }

        return if (before == null || before.artObject.principalOrFirstMaker != after.artObject.principalOrFirstMaker) {
            ListArtObjectUiState.ArtHeaderItem(after.artObject.principalOrFirstMaker)
        } else {
            null
        }
    }
}