package com.feature.common.domain.request

data class RegisterRequest(
    val username: String,
    val email: String,
    val password: String,
    val verifyPassword: String
)
