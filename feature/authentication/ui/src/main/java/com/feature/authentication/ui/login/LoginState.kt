package com.feature.authentication.ui.login

data class LoginState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val isLoginSuccess: Boolean = false,
    val errorFields: ErrorFields = ErrorFields.NONE,
    val errorApiResponse: ErrorApiResponse = ErrorApiResponse.NONE
)

enum class ErrorFields {
    NONE,
    EMAIL,
    USERNAME,
    PASSWORD,
    VERIFY_PASSWORD
}

enum class ErrorApiResponse {
    NONE,
    BAD_CREDENTIALS,
    SERVER,
    NETWORK
}
