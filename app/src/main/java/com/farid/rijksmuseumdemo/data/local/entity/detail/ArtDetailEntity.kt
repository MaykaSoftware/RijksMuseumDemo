package com.farid.rijksmuseumdemo.data.local.entity.detail

import kotlinx.serialization.Serializable

@Serializable
data class ArtDetailEntity(
    val artObject: ArtObjectDetailEntity
)