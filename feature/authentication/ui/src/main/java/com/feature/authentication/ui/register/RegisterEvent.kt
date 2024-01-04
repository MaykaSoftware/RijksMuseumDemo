package com.feature.authentication.ui.register


sealed class RegisterEvent {
    data class Register(
        val username: String,
        val email: String,
        val password: String,
        val verifyPassword: String
    ) : RegisterEvent()

    data class UpdateUsername(val username: String): RegisterEvent()
    data class UpdateEmail(val email: String): RegisterEvent()
    data class UpdatePassword(val password: String): RegisterEvent()
    data class UpdateVerifyPassword(val verifyPassword: String): RegisterEvent()
}