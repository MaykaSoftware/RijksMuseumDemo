package com.feature.authentication.data.datasource

import com.core.common.request.LoginRequest
import com.core.common.request.RegisterRequest
import com.core.network.DummyAuthService
import javax.inject.Inject

class AuthRemoteDataSource @Inject constructor(
    private val dummyAuthService: DummyAuthService
) {
    fun register(register: RegisterRequest): Result<com.core.common.dto.auth.LoginDTO> {
        return dummyAuthService.register(register)
    }

    fun login(login: LoginRequest): Result<com.core.common.dto.auth.LoginDTO> {
        return dummyAuthService.login(login)
    }
}