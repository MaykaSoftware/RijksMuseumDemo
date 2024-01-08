package com.feature.authentication.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.model.Resource
import com.feature.authentication.domain.use_cases.RegisterUseCase
import com.feature.authentication.ui.R
import com.feature.authentication.ui.validators.AuthParams
import com.feature.authentication.ui.validators.ValidatorFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val validatorFactory: ValidatorFactory
) : ViewModel() {

    private val _uiState = MutableStateFlow<RegisterUiState>(RegisterUiState.Default())
    val uiState: StateFlow<RegisterUiState> = _uiState
    fun onEvent(uiEvent: RegisterEvent) {
        when (uiEvent) {
            is RegisterEvent.FullNameChanged -> updateState { it.copy(fullName = uiEvent.fullName) }
            is RegisterEvent.EmailChanged -> updateState { it.copy(email = uiEvent.email) }
            is RegisterEvent.PasswordChanged -> updateState { it.copy(password = uiEvent.password) }
            is RegisterEvent.ConfirmPasswordChanged -> updateState { it.copy(passwordConfirm = uiEvent.password) }
            RegisterEvent.Register -> {
                if (areInputsValid()) {
                    register()
                }
            }

        }
    }

    private fun updateState(update: (RegisterUiState.Default) -> RegisterUiState.Default) {
        _uiState.value = (_uiState.value as? RegisterUiState.Default)?.let(update)
            ?: _uiState.value
    }

    private fun areInputsValid(): Boolean {
        val ui = (_uiState.value as? RegisterUiState.Default) ?: return false
        val fullNameError = validatorFactory.get(AuthParams.FULL_NAME).validate(ui.fullName)
        val emailError = validatorFactory.get(AuthParams.EMAIL).validate(ui.email)
        val passwordError = validatorFactory.get(AuthParams.PASSWORD).validate(ui.password)
        val confirmPasswordError =
            validatorFactory.get(AuthParams.PASSWORD).validate(ui.passwordConfirm)
        val passwordMatchError =
            if (confirmPasswordError.isValid && ui.passwordConfirm != ui.password) {
                R.string.password_match_error
            } else {
                null
            }

        val hasError = listOf(
            fullNameError,
            emailError,
            passwordError,
            confirmPasswordError,
        ).any { !it.isValid } || passwordMatchError != null

        _uiState.value = ui.copy(
            fullNameError = fullNameError.errorMessage,
            emailError = emailError.errorMessage,
            passwordError = passwordError.errorMessage,
            passwordConfirmError = confirmPasswordError.errorMessage ?: passwordMatchError,
        )
        return !hasError
    }

    private fun register() {
        viewModelScope.launch {
            val ui = (_uiState.value as? RegisterUiState.Default) ?: return@launch
            _uiState.value = RegisterUiState.Default(isLoading = true)
            registerUseCase.invoke(ui.fullName, ui.email, ui.password, ui.passwordConfirm).collect {
                when (it) {
                    is Resource.Error -> RegisterUiState.Default(signupError = R.string.unknown_error)
                    is Resource.Success -> RegisterUiState.Authenticated
                }
            }

        }
    }
}