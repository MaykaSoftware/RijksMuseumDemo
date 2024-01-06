package com.core.common.dto.auth

data class LoginDTO(
    val userID: Int,
    val token: String,
    val username: String,
    val email: String,
    val name: String
)