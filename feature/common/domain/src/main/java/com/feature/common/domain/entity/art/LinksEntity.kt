package com.feature.common.domain.entity.art

import kotlinx.serialization.Serializable

@Serializable
data class LinksEntity(
    val self: String,
    val web: String
)