package com.core.common.validator

interface InputValidator {
    fun validate(input: String): ValidationResult
}
