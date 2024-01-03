package com.feature.common.domain.dto.detail

import kotlinx.serialization.Serializable

@Serializable
data class ArtObjectDetailResponse(
    val description: String?,
    val hasImage: Boolean,
    val id: String,
    val language: String,
    val location: String?,
    val longTitle: String,
    val objectNumber: String,
    val showImage: Boolean,
    val subTitle: String,
    val title: String,
    val webImage: WebImageDetailResponse
)