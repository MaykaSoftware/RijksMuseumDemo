package com.feature.common.domain.dto.home

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArtObjectResponse(
    val hasImage: Boolean,
    @SerialName("headerImage")
    val headerImageResponse: HeaderImageResponse,
    val id: String,
    @SerialName("links")
    val linksResponse: LinksResponse,
    val longTitle: String,
    val objectNumber: String,
    val permitDownload: Boolean,
    val principalOrFirstMaker: String,
    val productionPlaces: List<String>,
    val showImage: Boolean,
    val title: String,
    @SerialName("webImage")
    val webImageResponse: WebImageResponse
)