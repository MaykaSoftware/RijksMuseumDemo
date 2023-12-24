package com.feature.common.domain.entity.art_detail

import kotlinx.serialization.Serializable

@Serializable
data class ArtDetailEntity(
    val artObject: ArtObjectDetailEntity
)