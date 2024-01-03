package com.feature.authentication.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.feature.authentication.domain.use_cases.AuthenticationResult
import com.feature.authentication.domain.use_cases.RegisterUseCase
import com.feature.authentication.ui.login.ErrorApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(RegisterState())
    val uiState: StateFlow<RegisterState> = _uiState.asStateFlow()
    fun onEvent(event: RegisterEvent){
        when(event){
            is RegisterEvent.Register -> {
               register()
            }

            is RegisterEvent.UpdateUsername -> {
                _uiState.value = uiState.value.copy(username = event.username)
            }
            is RegisterEvent.UpdateEmail -> {
                _uiState.value = uiState.value.copy(email = event.email)
            }
            is RegisterEvent.UpdatePassword -> {
                _uiState.value = uiState.value.copy(password = event.password)
            }
            is RegisterEvent.UpdateVerifyPassword -> {
                _uiState.value = uiState.value.copy(verifyPassword = event.verifyPassword)
            }
        }
    }

    private fun register(){
        viewModelScope.launch {
            registerUseCase.invoke(
                username = uiState.value.username,
                email = uiState.value.email,
                password = uiState.value.password,
                verifyPassword = uiState.value.verifyPassword
            ).collect {
                when(it){
                    is AuthenticationResult.Error -> {
                        when(it.throwable) {

                            is IOException -> {
                                _uiState.value = uiState.value.copy(errorApiResponse = ErrorApiResponse.NETWORK)
                            }
                            else -> {
                                _uiState.value = uiState.value.copy(errorApiResponse = ErrorApiResponse.SERVER)
                            }
                        }
                    }
                    is AuthenticationResult.Success -> {
                        _uiState.value = uiState.value.copy(isSuccessRegister = it.user.userID > 0)
                    }

                    is AuthenticationResult.ErrorField -> {
                       // _uiState.value = uiState.value.copy(errorFields = it.errorFields)
                    }
                }
            }
        }
    }
}