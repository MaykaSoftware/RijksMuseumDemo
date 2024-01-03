package com.feature.authentication.ui.register

import com.feature.authentication.ui.login.ErrorApiResponse
import com.feature.authentication.ui.login.ErrorFields

data class RegisterState(
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val verifyPassword: String = "",
    val isLoading: Boolean = false,
    val isSuccessRegister: Boolean = false,
    val errorFields: ErrorFields = ErrorFields.NONE,
    val errorApiResponse: ErrorApiResponse = ErrorApiResponse.NONE
)