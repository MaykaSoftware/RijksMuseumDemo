package com.feature.authentication.domain.use_cases

import com.feature.authentication.data.repository.AuthRepository
import com.feature.common.domain.model.auth.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(
        email: String,
        password: String
    ): Flow<AuthenticationResult> = flow {
        val result = authRepository.login(email, password)
        if (email.isBlank() || emailRegex(email)) {
            emit(
                AuthenticationResult.ErrorField(
                    ErrorFields.EMAIL
                ))
        } else if (password.isBlank() || password.length < 4) {
            emit(
                AuthenticationResult.ErrorField(
                    ErrorFields.PASSWORD
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