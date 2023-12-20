package com.farid.rijksmuseumdemo.data.mapper

import com.farid.rijksmuseumdemo.data.local.entity.detail.ArtObjectDetailEntity
import com.farid.rijksmuseumdemo.data.local.entity.detail.WebImageDetailEntity
import com.farid.rijksmuseumdemo.domain.model.detail.ArtObjectDetail
import com.farid.rijksmuseumdemo.domain.model.detail.WebImageDetail


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
