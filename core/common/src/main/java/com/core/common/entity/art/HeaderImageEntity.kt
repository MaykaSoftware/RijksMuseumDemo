package com.core.common.entity.art

import kotlinx.serialization.Serializable

@Serializable
data class HeaderImageEntity(
    val guid: String,
    val height: Int,
    val offsetPercentageX: Int,
    val offsetPercentageY: Int,
    val url: String,
    val width: Int
)