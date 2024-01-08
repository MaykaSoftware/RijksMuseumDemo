package com.feature.art.domain.use_cases


import com.core.common.model.art_detail.ArtObjectDetail
import com.feature.art.data.repo.ArtRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ArtDetailUseCase @Inject constructor(
    private val artRepository: ArtRepository
) {

    suspend operator fun invoke(objectNumber: String): Flow<Resource> = flow {
        if (objectNumber.isNotEmpty()) {
            emit(Resource.Loading)
            try {
                val artDetailObject = artRepository.getArtObject(objectNumber)
                emit(Resource.Success(artDetailObject))
            } catch (e: IOException) {
                emit(Resource.Error(ERROR.IO_ERROR))
            } catch (e: HttpException) {
                emit(Resource.Error(ERROR.HTTP_ERROR))
            }
        } else {
            emit(Resource.Error(ERROR.EMPTY_FIELD_EXCEPTION))
        }
    }
}

sealed class Resource {
    data object Loading : Resource()
    data class Success(val artObjectDetail: ArtObjectDetail?) : Resource()
    data class Error(val error: ERROR) : Resource()
}

enum class ERROR {
    HTTP_ERROR,
    IO_ERROR,
    EMPTY_FIELD_EXCEPTION
}