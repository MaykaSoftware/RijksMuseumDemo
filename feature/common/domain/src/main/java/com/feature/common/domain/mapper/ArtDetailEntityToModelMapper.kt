package com.feature.common.domain.mapper

import com.feature.common.domain.entity.art_detail.ArtObjectDetailEntity
import com.feature.common.domain.entity.art_detail.WebImageDetailEntity
import com.feature.common.domain.model.art_detail.ArtObjectDetail
import com.feature.common.domain.model.art_detail.WebImageDetail


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
