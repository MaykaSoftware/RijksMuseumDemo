package com.feature.authentication.data.repository

import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun register(
        username: String,
        email: String,
        password: String,
        verifyPassword: String
    ): Result<com.core.common.dto.auth.LoginDTO>

    suspend fun login(email: String, password: String): Result<com.core.common.dto.auth.LoginDTO>
    suspend fun saveToken(token: String)
    fun readToken(): Flow<String>
}