package com.farid.rijksmuseumdemo.domain.repository

import androidx.paging.PagingData
import com.farid.rijksmuseumdemo.domain.model.detail.ArtObjectDetail
import com.farid.rijksmuseumdemo.domain.model.home.ArtObject
import kotlinx.coroutines.flow.Flow
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.HttpException
import retrofit2.Response

class FakeFailureArtRepository: ArtRepository {
    override val artObjectData: Flow<PagingData<ArtObject>>
        get() = TODO("Not yet implemented")

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