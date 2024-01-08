package com.feature.authentication.ui.register

import androidx.annotation.StringRes
sealed class RegisterUiState {

    data object Authenticated : RegisterUiState()

    data class Default(
        val fullName: String = "",
        @StringRes val fullNameError: Int? = null,

        val email: String = "",
        @StringRes val emailError: Int? = null,

        val password: String = "",
        @StringRes val passwordError: Int? = null,

        val passwordConfirm: String = "",
        @StringRes val passwordConfirmError: Int? = null,

        val isLoading: Boolean = false,

        @StringRes val signupError: Int? = null,
    ) : RegisterUiState()
}