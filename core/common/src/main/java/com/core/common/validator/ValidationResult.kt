package com.core.common.validator

data class ValidationResult(
    val errorMessage: Int? = null,
) {
    val isValid: Boolean
        get() = errorMessage == null
}
