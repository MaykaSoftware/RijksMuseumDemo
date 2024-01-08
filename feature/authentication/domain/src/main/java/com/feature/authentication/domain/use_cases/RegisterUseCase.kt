package com.feature.authentication.domain.use_cases

import com.core.common.enums.ResourceError
import com.core.common.model.Resource
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
    ): Flow<Resource<User>> = flow {
        val result = authRepository.register(username, email, password, verifyPassword)
        result.onSuccess {
            emit(
                Resource.Success(
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
            //TODO FINISH EXCEPTION HANDLING LATER
            emit(Resource.Error(ResourceError.UNKNOWN))
        }
    }

}