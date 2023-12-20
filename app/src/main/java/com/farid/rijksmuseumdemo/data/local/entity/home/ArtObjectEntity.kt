package com.farid.rijksmuseumdemo.data.local.entity.home

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity("art_object")
@Serializable
data class ArtObjectEntity(
    val hasImage: Boolean,
    val headerImageEntity: HeaderImageEntity,
    @PrimaryKey
    val id: String,
    val linksEntity: LinksEntity,
    val longTitle: String,
    val objectNumber: String,
    val permitDownload: Boolean,
    val principalOrFirstMaker: String,
    val productionPlaces: List<String>,
    val showImage: Boolean,
    val title: String,
    val webImageEntity: WebImageEntity,
    var page: Int
)