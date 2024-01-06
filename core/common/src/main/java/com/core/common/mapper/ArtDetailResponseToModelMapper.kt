package com.core.common.mapper

import com.core.common.dto.detail.ArtObjectDetailResponse
import com.core.common.dto.detail.WebImageDetailResponse
import com.core.common.model.art_detail.ArtObjectDetail
import com.core.common.model.art_detail.WebImageDetail


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