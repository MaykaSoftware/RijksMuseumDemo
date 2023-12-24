package com.feature.art_details.domain.use_cases


import com.feature.art_details.repository.ArtDetailsRepository
import com.feature.common.domain.model.art_detail.ArtObjectDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ArtDetailUseCase @Inject constructor(
    private val artDetailsRepository: ArtDetailsRepository
) {

    /**
     * A flow from the database is not necessary for this use case. This function is a better
     * choice then
     * **/

    suspend operator fun invoke(objectNumber: String): Flow<Resource> = flow {
        if (objectNumber.isNotEmpty()){
            emit(Resource.Loading)
            try {
                val artDetailObject = artDetailsRepository.getArtObject(objectNumber)
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

    suspend fun getArtObjectDetailFlow(objectNumber: String): Flow<Resource> = flow {
        if (objectNumber.isNotEmpty()){
            emit(Resource.Loading)
            try {
                val flow = artDetailsRepository.getArtObjectFlow(objectNumber).map {
                    Resource.Success(it)
                }.firstOrNull()
                if (flow != null) {
                    emit(flow)
                } else {
                    emit(Resource.Error(ERROR.HTTP_ERROR))
                }
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