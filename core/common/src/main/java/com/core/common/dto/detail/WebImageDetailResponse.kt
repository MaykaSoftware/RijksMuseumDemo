package com.core.common.dto.detail

import kotlinx.serialization.Serializable

@Serializable
data class WebImageDetailResponse(
    val guid: String,
    val height: Int,
    val offsetPercentageX: Int,
    val offsetPercentageY: Int,
    val url: String,
    val width: Int
)