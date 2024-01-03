package com.feature.common.domain.mapper

import com.feature.common.domain.dto.detail.ArtObjectDetailResponse
import com.feature.common.domain.dto.detail.WebImageDetailResponse
import com.feature.common.domain.model.art_detail.ArtObjectDetail
import com.feature.common.domain.model.art_detail.WebImageDetail


fun ArtObjectDetailResponse.toArtObjectDetail(): ArtObjectDetail {
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

fun WebImageDetailResponse.toWebImageDetail(): WebImageDetail {
    return WebImageDetail(
        guid = guid,
        height = height,
        offsetPercentageX = offsetPercentageX,
        offsetPercentageY = offsetPercentageY,
        url = url,
        width = width
    )
}