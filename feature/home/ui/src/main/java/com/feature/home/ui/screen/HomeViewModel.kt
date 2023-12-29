package com.feature.home.ui.screen

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

) : ViewModel() {
    private val mutableState = MutableStateFlow<State>(State.Loading)
    val state = mutableState.asStateFlow()
}

sealed class State {
    data object Loading : State()
    data object Success : State()
    data class Error(val errorMessage: String) : State()
}