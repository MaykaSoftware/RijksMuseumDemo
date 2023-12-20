package com.farid.rijksmuseumdemo.data.local.entity.detail

import kotlinx.serialization.Serializable

@Serializable
data class WebImageDetailEntity(
    val guid: String,
    val height: Int,
    val offsetPercentageX: Int,
    val offsetPercentageY: Int,
    val url: String,
    val width: Int
)