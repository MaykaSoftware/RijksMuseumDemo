package com.feature.authentication.ui.validators

import com.core.common.validator.InputValidator
import javax.inject.Inject

class ValidatorFactory @Inject constructor() {

    private val validators: Map<AuthParams, InputValidator> = mapOf(
        AuthParams.FULL_NAME to FullNameValidator(),
        AuthParams.EMAIL to EmailValidator(),
        AuthParams.PASSWORD to PasswordValidator(),
    )

    fun get(param: AuthParams): InputValidator {
        return validators.getOrElse(param) {
            throw IllegalArgumentException("Validator not found; make sure you have provided correct param")
        }
    }
}
