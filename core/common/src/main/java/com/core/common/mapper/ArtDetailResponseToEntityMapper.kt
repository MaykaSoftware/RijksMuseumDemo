package com.core.common.mapper

import com.core.common.entity.art_detail.ArtDetailEntity
import com.core.common.entity.art_detail.ArtObjectDetailEntity
import com.core.common.entity.art_detail.WebImageDetailEntity


fun com.core.common.dto.detail.ArtDetailResponse.toArtDetailEntity(): ArtDetailEntity {
    return ArtDetailEntity(
        artObject = artObject.toArtObjectDetailEntity()
    )
}

fun com.core.common.dto.detail.ArtObjectDetailResponse.toArtObjectDetailEntity(): ArtObjectDetailEntity {
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

fun com.core.common.dto.detail.WebImageDetailResponse.toWebImageDetailEntity(): WebImageDetailEntity {
    return WebImageDetailEntity(
        guid = guid,
        height = height,
        offsetPercentageX = offsetPercentageX,
        offsetPercentageY = offsetPercentageY,
        url = url,
        width = width
    )
}