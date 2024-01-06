package com.feature.authentication.domain.use_cases

import com.core.common.model.auth.User
import com.feature.authentication.data.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(
        username: String,
        email: String,
        password: String,
        verifyPassword: String
    ): Flow<AuthenticationResult> = flow {
        val result = authRepository.register(username, email, password, verifyPassword)
        if (username.isBlank() || username.length < 2) {
            emit(
                AuthenticationResult.ErrorField(
                    ErrorFields.USERNAME
                )
            )
        } else if (email.isBlank() || emailRegex(email)) {
            emit(
                AuthenticationResult.ErrorField(
                    ErrorFields.EMAIL
                )
            )
        } else if (password.isBlank() || (password.length < 4)) {
            emit(
                AuthenticationResult.ErrorField(
                    ErrorFields.PASSWORD
                )
            )
        } else if (password != verifyPassword) {
            emit(
                AuthenticationResult.ErrorField(
                    ErrorFields.VERIFY_PASSWORD
                )
            )
        } else {
            result.onSuccess {
                emit(
                    AuthenticationResult.Success(
                        User(
                            it.userID,
                            it.username,
                            it.email,
                            it.name
                        )
                    )
                )
            }

            result.onFailure {
                emit(AuthenticationResult.Error(it))
            }
        }
    }
}

fun emailRegex(email: String): Boolean {
    val regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$"
    return !email.matches(regex.toRegex())
}