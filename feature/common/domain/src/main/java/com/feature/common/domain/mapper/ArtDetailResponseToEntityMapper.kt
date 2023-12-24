package com.feature.common.domain.mapper

import com.farid.rijksmuseumdemo.data.remote.dto.detail.ArtDetailResponse
import com.farid.rijksmuseumdemo.data.remote.dto.detail.ArtObjectDetailResponse
import com.farid.rijksmuseumdemo.data.remote.dto.detail.WebImageDetailResponse

fun ArtDetailResponse.toArtDetailEntity(): com.feature.common.domain.entity.art_detail.ArtDetailEntity {
    return com.feature.common.domain.entity.art_detail.ArtDetailEntity(
        artObject = artObject.toArtObjectDetailEntity()
    )
}

fun ArtObjectDetailResponse.toArtObjectDetailEntity(): com.feature.common.domain.entity.art_detail.ArtObjectDetailEntity {
    return com.feature.common.domain.entity.art_detail.ArtObjectDetailEntity(
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

fun WebImageDetailResponse.toWebImageDetailEntity(): com.feature.common.domain.entity.art_detail.WebImageDetailEntity {
    return com.feature.common.domain.entity.art_detail.WebImageDetailEntity(
        guid = guid,
        height = height,
        offsetPercentageX = offsetPercentageX,
        offsetPercentageY = offsetPercentageY,
        url = url,
        width = width
    )
}