package com.feature.authentication.data.repository

import com.feature.common.domain.dto.auth.LoginDTO
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun register(
        username: String,
        email: String,
        password: String,
        verifyPassword: String
    ): Result<LoginDTO>

    suspend fun login(email: String, password: String):  Result<LoginDTO>
    suspend fun saveToken(token: String)
    fun readToken(): Flow<String>
}