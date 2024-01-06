package com.core.common.dto.detail

import kotlinx.serialization.Serializable

@Serializable
data class ArtDetailResponse(
    val artObject: ArtObjectDetailResponse
)