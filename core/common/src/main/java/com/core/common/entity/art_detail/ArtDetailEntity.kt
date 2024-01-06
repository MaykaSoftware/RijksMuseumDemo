package com.core.common.entity.art_detail

import kotlinx.serialization.Serializable

@Serializable
data class ArtDetailEntity(
    val artObject: ArtObjectDetailEntity
)