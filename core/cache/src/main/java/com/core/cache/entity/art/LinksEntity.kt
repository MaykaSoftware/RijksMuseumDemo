package com.core.cache.entity.art

import kotlinx.serialization.Serializable

@Serializable
data class LinksEntity(
    val self: String,
    val web: String
)