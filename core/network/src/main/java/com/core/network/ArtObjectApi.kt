package com.core.network



import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ArtObjectApi {

    @GET("{culture}/collection/")
    suspend fun getMuseumObjects(
        @Path("culture") language: String,
        @Query("p") page: Int,
        @Query("ps") pageCount: Int,
        @Query("s") artist: String = "artist",
        @Query("imgonly") hasImage: Boolean = true
    ): com.core.common.dto.home.ArtResponse

    @GET("{culture}/collection/{object-number}")
    suspend fun getMuseumObject(
        @Path("culture") language: String,
        @Path("object-number") objectNumber: String
    ): com.core.common.dto.detail.ArtDetailResponse

    companion object {
        const val BASE_URL = "https://www.rijksmuseum.nl/api/"
        const val API_KEY = "0fiuZFh4"
    }
}