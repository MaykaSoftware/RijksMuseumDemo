package com.feature.art.ui.screen.art_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.feature.art.domain.use_cases.ArtDetailUseCase
import com.feature.art.domain.use_cases.ERROR
import com.feature.art.domain.use_cases.Resource
import com.feature.common.domain.model.art_detail.ArtObjectDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val artDetailUseCase: ArtDetailUseCase
) : ViewModel() {

    private val mutableState = MutableStateFlow<State>(State.Loading)
    val state = mutableState.asStateFlow()

    init {
        viewModelScope.launch {
            val objectNumber = savedStateHandle.get<String>("objectNumber") ?: return@launch
            artDetailUseCase.invoke(objectNumber).collect { result ->
                when (result) {
                    is Resource.Error -> {
                        when (result.error) {
                            ERROR.HTTP_ERROR -> {
                                mutableState.value = State.Error("Something went wrong")
                            }

                            ERROR.IO_ERROR -> {
                                mutableState.value = State.Error("There is no internet connection")
                            }

                            ERROR.EMPTY_FIELD_EXCEPTION -> {
                                mutableState.value = State.Error("Empty object number")
                            }
                        }
                    }

                    Resource.Loading -> {
                        mutableState.value = State.Loading
                    }

                    is Resource.Success -> {
                        mutableState.value = State.Success(result.artObjectDetail)
                    }
                }
            }

        }
    }
}

sealed class State {
    data object Loading : State()
    data class Success(val artObjectDetail: ArtObjectDetail?) : State()
    data class Error(val errorMessage: String) : State()
}