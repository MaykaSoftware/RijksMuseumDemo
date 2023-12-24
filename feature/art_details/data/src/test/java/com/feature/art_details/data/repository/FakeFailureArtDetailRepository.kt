package com.feature.art_details.data.repository

import com.feature.art_details.repository.ArtDetailsRepository
import com.feature.common.domain.model.art_detail.ArtObjectDetail
import kotlinx.coroutines.flow.Flow
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.HttpException
import retrofit2.Response

class FakeFailureArtDetailRepository: ArtDetailsRepository {
    override suspend fun getArtObjectFlow(objectNumber: String): Flow<ArtObjectDetail> {
        throw HttpException(Response.error<ResponseBody>(500 ,
            "".toResponseBody("plain/text".toMediaType())
        ))
    }

    override suspend fun getArtObject(objectNumber: String): ArtObjectDetail {
        throw HttpException(Response.error<ResponseBody>(500 ,
            "".toResponseBody("plain/text".toMediaType())
        ))
    }
}