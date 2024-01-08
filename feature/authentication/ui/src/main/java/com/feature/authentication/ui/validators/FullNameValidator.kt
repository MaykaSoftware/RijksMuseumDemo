package com.feature.authentication.ui.validators

import com.core.common.validator.InputValidator
import com.core.common.validator.ValidationResult
import com.feature.authentication.ui.R

class FullNameValidator : InputValidator {

    private val namePattern = Regex(
        "/^[a-zA-Z]+(?:[\\s.]+[a-zA-Z]+)*\$/g",
        RegexOption.IGNORE_CASE,
    )

    override fun validate(input: String): ValidationResult {
        return if (input.isEmpty()) {
            ValidationResult(R.string.error_name_empty)
        } else if (namePattern.matches(input)) {
            return ValidationResult(R.string.error_name_invalid)
        } else {
            ValidationResult()
        }
    }
}
