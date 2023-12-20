package com.farid.rijksmuseumdemo.data.remote.dto.detail

import kotlinx.serialization.Serializable

@Serializable
data class ArtDetailResponse(
    val artObject: ArtObjectDetailResponse
)