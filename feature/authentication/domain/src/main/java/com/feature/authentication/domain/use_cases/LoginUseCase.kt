package com.feature.authentication.domain.use_cases

import com.core.common.enums.ResourceError
import com.core.common.model.Resource
import com.core.common.model.auth.User
import com.feature.authentication.data.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(
        email: String,
        password: String
    ): Flow<Resource<User>> = flow {
        val result = authRepository.login(email, password)
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
