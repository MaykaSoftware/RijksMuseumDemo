package com.core.common.dto.home

import kotlinx.serialization.Serializable

@Serializable
data class HeaderImageResponse(
    val guid: String,
    val height: Int,
    val offsetPercentageX: Int,
    val offsetPercentageY: Int,
    val url: String,
    val width: Int
)