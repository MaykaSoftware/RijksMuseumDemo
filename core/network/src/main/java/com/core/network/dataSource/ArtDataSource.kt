package com.core.network.dataSource

import com.core.network.ArtObjectApi
import javax.inject.Inject

class ArtDataSource @Inject constructor(
    private val artObjectApi: ArtObjectApi
) {
    suspend fun getMuseumObjects(
        language: String,
        page: Int,
        pageCount: Int
    ) = artObjectApi.getMuseumObjects(language, page, pageCount)

    suspend fun getMuseumObject(
        language: String,
        objectNumber: String
    ) = artObjectApi.getMuseumObject(language, objectNumber)
}