package com.feature.authentication.data.datasource

import com.core.network.AuthService
import com.core.network.DummyAuthService
import com.feature.common.domain.dto.auth.LoginDTO
import com.feature.common.domain.request.LoginRequest
import com.feature.common.domain.request.RegisterRequest
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRemoteDataSource @Inject constructor(
    private val dummyAuthService: DummyAuthService
) {
    suspend fun register(register: RegisterRequest): Result<LoginDTO> {
        return dummyAuthService.register(register)
    }
    suspend fun login(login: LoginRequest): Result<LoginDTO> {
        return dummyAuthService.login(login)
    }
}