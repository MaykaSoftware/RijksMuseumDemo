package com.farid.rijksmuseumdemo.data.local.entity.home

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