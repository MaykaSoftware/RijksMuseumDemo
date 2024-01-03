package com.feature.common.domain.dto.home

import kotlinx.serialization.Serializable

@Serializable
data class LinksResponse(
    val self: String,
    val web: String
)