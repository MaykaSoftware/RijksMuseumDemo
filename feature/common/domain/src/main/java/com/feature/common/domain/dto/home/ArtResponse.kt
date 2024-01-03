package com.feature.common.domain.dto.home

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArtResponse(
    @SerialName("artObjects")
    val artObjectResponses: List<ArtObjectResponse>,
    val count: Int,
    @SerialName("countFacets")
    val countFacetsResponse: CountFacetsResponse,
    val elapsedMilliseconds: Int,
    @SerialName("facets")
    val facetResponses: List<FacetsResponse>
)