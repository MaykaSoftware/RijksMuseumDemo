package com.feature.authentication.domain.use_cases

import com.core.common.model.auth.User

sealed class AuthenticationResult {
    data class Success(val user: User): AuthenticationResult()
    data class Error(val throwable: Throwable): AuthenticationResult()
    data class ErrorField(val errorFields: ErrorFields): AuthenticationResult()
}
enum class ErrorFields {
    EMAIL,
    USERNAME,
    PASSWORD,
    VERIFY_PASSWORD
}