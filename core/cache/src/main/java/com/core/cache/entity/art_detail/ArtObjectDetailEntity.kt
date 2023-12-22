package com.core.cache.entity.art_detail

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity("art_detail_object")
@Serializable
data class ArtObjectDetailEntity(
    val description: String?,
    val hasImage: Boolean,
    @PrimaryKey
    val objectNumber: String,
    val id: String,
    val language: String,
    val longTitle: String,
    val showImage: Boolean,
    val subTitle: String,
    val title: String,
    val webImage: WebImageDetailEntity
)