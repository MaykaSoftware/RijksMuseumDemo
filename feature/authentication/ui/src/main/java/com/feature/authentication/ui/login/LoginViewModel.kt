package com.feature.authentication.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.feature.authentication.domain.use_cases.AuthenticationResult
import com.feature.authentication.domain.use_cases.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
): ViewModel(){

    private val _uiState = MutableStateFlow(LoginState())
    val uiState: StateFlow<LoginState> = _uiState.asStateFlow()
    fun onEvent(event: LoginEvent){
        when(event){
            is LoginEvent.Login -> {
                login()
            }
            is LoginEvent.UpdateEmail -> {
                _uiState.value = uiState.value.copy(email = event.email)
            }
            is LoginEvent.UpdatePassword -> {
                _uiState.value = uiState.value.copy(password = event.password)
            }
        }
    }

    private fun login(){
        viewModelScope.launch {
            loginUseCase.invoke(
                email = uiState.value.email,
                password = uiState.value.password
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
                        _uiState.value = uiState.value.copy(isLoginSuccess = it.user.userID > 0)
                    }

                    is AuthenticationResult.ErrorField -> {
                        //_uiState.value = uiState.value.copy(errorFields = it.errorFields)
                    }
                }
            }
        }
    }
}