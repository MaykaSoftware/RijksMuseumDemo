package com.farid.rijksmuseumdemo.data.remote.dto.home

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CountFacetsResponse(
    @SerialName("hasimage")
    val hasImage: Int,
    @SerialName("ondisplay")
    val onDisplay: Int
)