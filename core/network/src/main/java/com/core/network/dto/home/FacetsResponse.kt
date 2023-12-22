package com.farid.rijksmuseumdemo.data.remote.dto.home

import kotlinx.serialization.Serializable

@Serializable
data class FacetsResponse(
    val facets: List<FacetResponse>,
    val name: String,
    val otherTerms: Int,
    val prettyName: Int
)