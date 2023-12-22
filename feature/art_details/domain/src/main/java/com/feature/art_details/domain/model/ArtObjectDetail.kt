package com.feature.art_details.domain.model

data class ArtObjectDetail(
    val description: String?,
    val hasImage: Boolean,
    val objectNumber: String,
    val id: String,
    val language: String,
    val longTitle: String,
    val showImage: Boolean,
    val subTitle: String,
    val title: String,
    val webImage: WebImageDetail
)