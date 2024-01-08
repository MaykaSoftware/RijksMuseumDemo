package com.core.network


import com.core.common.request.LoginRequest
import com.core.common.request.RegisterRequest
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
    ): Result<com.core.common.dto.auth.LoginDTO>

    @GET("login")
    suspend fun login(
        @Body login: LoginRequest
    ): Result<com.core.common.dto.auth.LoginDTO>
}

@Singleton
class DummyAuthService @Inject constructor() {
    fun register(register: RegisterRequest): Result<com.core.common.dto.auth.LoginDTO> {
        return if (register.password != "123456") {
            Result.success(
                com.core.common.dto.auth.LoginDTO(
                    1,
                    "token",
                    "abc",
                    "abc@example.com",
                    "Adam Yousef"
                )
            )
        } else {
            Result.failure(HttpException(Response.error<Any>(401, createDummyResponseBody())))
        }
    }

    fun login(login: LoginRequest): Result<com.core.common.dto.auth.LoginDTO> {
        return if (login.username == "abc@example.com" && login.password == "abcdef") {
            Result.success(
                com.core.common.dto.auth.LoginDTO(
                    1,
                    "token",
                    "Adam",
                    "abc@example.com",
                    "Adam Yousef"
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

