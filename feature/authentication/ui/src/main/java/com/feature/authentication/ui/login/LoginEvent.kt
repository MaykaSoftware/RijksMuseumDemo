package com.feature.authentication.ui.login

sealed class LoginEvent {
    data class Login(
        val username: String,
        val password: String
    ) : LoginEvent()
    data class UpdateEmail(val email: String): LoginEvent()
    data class UpdatePassword(val password: String): LoginEvent()
}