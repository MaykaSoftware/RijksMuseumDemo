package com.feature.authentication.data.repository

import com.core.common.dto.auth.LoginDTO
import com.core.common.entity.auth.toUserEntity
import com.core.common.model.auth.User
import com.core.common.request.LoginRequest
import com.core.common.request.RegisterRequest
import com.feature.authentication.data.datasource.AuthCacheDataSource
import com.feature.authentication.data.datasource.AuthRemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource,
    private val authCacheDataSource: AuthCacheDataSource
) : AuthRepository {
    override suspend fun register(
        username: String,
        email: String,
        password: String,
        verifyPassword: String
    ): Result<LoginDTO> {
        val result = authRemoteDataSource.register(
            RegisterRequest(
                username,
                email,
                password,
                verifyPassword
            )
        )

        result.onSuccess {
            authCacheDataSource.saveToken(it.token)
            val user = User(
                it.userID,
                it.username,
                it.email,
                it.name
            )
            authCacheDataSource.upsert(user.toUserEntity())

        }
        return result
    }

    override suspend fun login(email: String, password: String): Result<LoginDTO> {
        val result = authRemoteDataSource.login(
            LoginRequest(
                email,
                password
            )
        )

        result.onSuccess {
            authCacheDataSource.saveToken(it.token)
            val user = User(
                it.userID,
                it.username,
                it.email,
                it.name
            )
            authCacheDataSource.upsert(user.toUserEntity())
        }

        return result
    }

    override suspend fun saveToken(token: String) {
        authCacheDataSource.saveToken(token)
    }

    override fun readToken(): Flow<String> = authCacheDataSource.readToken()
}