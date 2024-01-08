package com.feature.authentication.ui.validators

import com.core.common.validator.InputValidator
import com.core.common.validator.ValidationResult
import com.feature.authentication.ui.R

class EmailValidator : InputValidator {

    private val emailPattern = Regex(
        "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,}$",
        RegexOption.IGNORE_CASE,
    )

    override fun validate(input: String): ValidationResult {
        return if (input.isEmpty()) {
            ValidationResult(R.string.error_email_empty)
        } else if (!emailPattern.matches(input)) {
            return ValidationResult(R.string.error_email_invalid)
        } else {
            ValidationResult()
        }
    }
}
