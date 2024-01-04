package com.feature.common.domain.dto.detail

import kotlinx.serialization.Serializable

@Serializable
data class ArtDetailResponse(
    val artObject: ArtObjectDetailResponse
)