package com.feature.art_details.mapper

import com.core.cache.entity.art_detail.ArtDetailEntity
import com.core.cache.entity.art_detail.ArtObjectDetailEntity
import com.core.cache.entity.art_detail.WebImageDetailEntity
import com.farid.rijksmuseumdemo.data.remote.dto.detail.ArtDetailResponse
import com.farid.rijksmuseumdemo.data.remote.dto.detail.ArtObjectDetailResponse
import com.farid.rijksmuseumdemo.data.remote.dto.detail.WebImageDetailResponse

fun ArtDetailResponse.toArtDetail(): ArtDetailEntity {
    return ArtDetailEntity(
        artObject = artObject.toArtObjectDetailEntity()
    )
}

fun ArtObjectDetailResponse.toArtObjectDetailEntity(): ArtObjectDetailEntity {
    return ArtObjectDetailEntity(
        description = description,
        hasImage = hasImage,
        objectNumber = objectNumber,
        id = id,
        language = language,
        longTitle = longTitle,
        showImage = showImage,
        subTitle = subTitle,
        title = title,
        webImage = webImage.toWebImageDetailEntity()
    )
}

fun WebImageDetailResponse.toWebImageDetailEntity(): WebImageDetailEntity {
    return WebImageDetailEntity(
        guid = guid,
        height = height,
        offsetPercentageX = offsetPercentageX,
        offsetPercentageY = offsetPercentageY,
        url = url,
        width = width
    )
}