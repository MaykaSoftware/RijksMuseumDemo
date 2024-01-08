package com.feature.authentication.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.enums.ResourceError
import com.core.common.model.Resource
import com.feature.authentication.domain.use_cases.LoginUseCase
import com.feature.authentication.ui.R
import com.feature.authentication.ui.validators.AuthParams
import com.feature.authentication.ui.validators.ValidatorFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val validatorFactory: ValidatorFactory,
) : ViewModel() {

    private val _uiState = MutableStateFlow<LoginUiState>(LoginUiState.NotAuthenticated())
    val uiState: StateFlow<LoginUiState> = _uiState
    fun onEvent(uiEvent: LoginUiEvent) {
        when (uiEvent) {
            is LoginUiEvent.EmailChanged -> updateState { it.copy(email = uiEvent.email) }
            is LoginUiEvent.PasswordChanged -> updateState { it.copy(password = uiEvent.password) }
            LoginUiEvent.Login -> {
                if (areInputsValid()) {
                    login()
                }
            }

            else -> Unit
        }
    }

    private fun updateState(update: (LoginUiState.NotAuthenticated) -> LoginUiState.NotAuthenticated) {
        _uiState.value = (_uiState.value as? LoginUiState.NotAuthenticated)?.let(update)
            ?: _uiState.value
    }

//    private fun login(){
//
//        viewModelScope.launch {
//            loginUseCase.invoke(
//                email = uiState.value.email,
//                password = uiState.value.password
//            ).collect {
//                when(it){
//                    is AuthenticationResult.Error -> {
//                        when(it.throwable) {
//
//                            is IOException -> {
//                                _uiState.value = uiState.value.copy(errorApiResponse = ErrorApiResponse.NETWORK)
//                            }
//                            else -> {
//                                _uiState.value = uiState.value.copy(errorApiResponse = ErrorApiResponse.SERVER)
//                            }
//                        }
//                    }
//                    is AuthenticationResult.Success -> {
//                        _uiState.value = uiState.value.copy(isLoginSuccess = it.user.userID > 0)
//                    }
//
//                    is AuthenticationResult.ErrorField -> {
//                        _uiState.value = uiState.value.copy(errorFields = it.errorFields)
//                    }
//                }
//            }
//        }
//    }

    private fun login() = viewModelScope.launch {
        val ui = (_uiState.value as? LoginUiState.NotAuthenticated) ?: return@launch
        _uiState.value = LoginUiState.NotAuthenticated(isLoading = true)
        loginUseCase.invoke(ui.email, ui.password).collect {
            when (it) {
                is Resource.Error -> LoginUiState.NotAuthenticated(loginError = getError(it))
                is Resource.Success -> LoginUiState.Authenticated
            }
        }
    }

    private fun areInputsValid(): Boolean {
        val ui = (_uiState.value as? LoginUiState.NotAuthenticated) ?: return false
        val emailError = validatorFactory.get(AuthParams.EMAIL).validate(ui.email)
        val passwordError = validatorFactory.get(AuthParams.PASSWORD).validate(ui.password)
        val hasError = listOf(emailError, passwordError).any { !it.isValid }
        _uiState.value = ui.copy(
            emailError = emailError.errorMessage,
            passwordError = passwordError.errorMessage,
        )
        return !hasError
    }

    private fun getError(loginError: Resource.Error): Int {
        return when (loginError.e) {
            ResourceError.UNAUTHORIZED -> R.string.invalid_email_password
            ResourceError.SERVICE_UNAVAILABLE -> R.string.service_unavailable
            ResourceError.UNKNOWN -> R.string.unknown_error
        }
    }
}