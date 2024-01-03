package com.core.network

import com.feature.common.domain.dto.auth.LoginDTO
import com.feature.common.domain.request.LoginRequest
import com.feature.common.domain.request.RegisterRequest
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import javax.inject.Inject
import javax.inject.Singleton

interface AuthService {
    @GET("register")
    suspend fun register(
        @Body register: RegisterRequest
    ): Result<LoginDTO>

    @GET("login")
    suspend fun login(
        @Body login: LoginRequest
    ): Result<LoginDTO>
}

@Singleton
class DummyAuthService @Inject constructor() {
    suspend fun register(register: RegisterRequest): Result<LoginDTO> {
        return if(register.password != "123456") {
            Result.success(
                LoginDTO(
                    1,
                    "token",
                    "Farid",
                    "farid@example.com",
                    "Farid Benhaimoud"
                )
            )
        } else {
            Result.failure(HttpException(Response.error<Any>(401, createDummyResponseBody())))
        }
    }

    suspend fun login(login: LoginRequest): Result<LoginDTO> {
        return if(login.username == "farid@qbus-ict.nl" && login.password == "farid01"){
            Result.success(
                LoginDTO(
                    1,
                    "token",
                    "Farid",
                    "farid@example.com",
                    "Farid Benhaimoud"
                )
            )
        } else {
            Result.failure(HttpException(Response.error<Any>(401, createDummyResponseBody())))
        }
    }

    private fun createDummyResponseBody(): ResponseBody {
        val customResponse = CustomResponse("This is a dummy response")
        val json = """{"message":"${customResponse.message}"}"""

        val contentType = "application/json".toMediaType()
        return json.toResponseBody(contentType)
    }
}

data class CustomResponse(val message: String)

