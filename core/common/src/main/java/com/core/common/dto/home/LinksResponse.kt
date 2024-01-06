package com.core.common.dto.home

import kotlinx.serialization.Serializable

@Serializable
data class LinksResponse(
    val self: String,
    val web: String
)