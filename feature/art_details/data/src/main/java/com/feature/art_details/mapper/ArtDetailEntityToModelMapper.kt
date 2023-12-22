package com.feature.art_details.mapper

import com.core.cache.entity.art_detail.ArtObjectDetailEntity
import com.core.cache.entity.art_detail.WebImageDetailEntity
import com.feature.art_details.domain.model.ArtObjectDetail
import com.feature.art_details.domain.model.WebImageDetail


fun ArtObjectDetailEntity.toArtObjectDetail(): ArtObjectDetail {
    return ArtObjectDetail(
        description = description,
        hasImage = hasImage,
        objectNumber = objectNumber,
        id = id,
        language = language,
        longTitle = longTitle,
        showImage = showImage,
        subTitle = subTitle,
        title = title,
        webImage = webImage.toWebImageDetail()
    )
}

fun WebImageDetailEntity.toWebImageDetail(): WebImageDetail {
    return WebImageDetail(
        guid = guid,
        height = height,
        offsetPercentageX = offsetPercentageX,
        offsetPercentageY = offsetPercentageY,
        url = url,
        width = width
    )
}
