package com.core.common.dto.home

import kotlinx.serialization.Serializable

@Serializable
data class FacetResponse(
    val key: String,
    val value: Int
)