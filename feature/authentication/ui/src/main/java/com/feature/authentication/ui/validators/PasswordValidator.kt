package com.feature.authentication.ui.validators

import com.core.common.validator.InputValidator
import com.core.common.validator.ValidationResult
import com.feature.authentication.ui.R

class PasswordValidator : InputValidator {

    override fun validate(input: String): ValidationResult {
        return if (input.length < 6) {
            ValidationResult(R.string.label_error_password)
        } else {
            ValidationResult()
        }
    }
}
