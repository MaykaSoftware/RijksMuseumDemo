package com.farid.rijksmuseumdemo.data.local.entity.home

import kotlinx.serialization.Serializable

@Serializable
data class LinksEntity(
    val self: String,
    val web: String
)