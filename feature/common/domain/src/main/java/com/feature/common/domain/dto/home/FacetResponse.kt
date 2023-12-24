package com.farid.rijksmuseumdemo.data.remote.dto.home

import kotlinx.serialization.Serializable

@Serializable
data class FacetResponse(
    val key: String,
    val value: Int
)