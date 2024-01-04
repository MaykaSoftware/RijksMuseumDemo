package com.feature.common.domain.mapper

import com.feature.common.domain.model.art.ArtObject
import com.feature.common.domain.model.art.HeaderImage
import com.feature.common.domain.model.art.Links
import com.feature.common.domain.model.art.WebImage


fun com.feature.common.domain.entity.art.ArtObjectEntity.toArtObject(): ArtObject {
    return ArtObject(
        hasImage = hasImage,
        headerImage = headerImageEntity.toHeaderImage(),
        id = id,
        links = linksEntity.toLinks(),
        longTitle = longTitle,
        objectNumber = objectNumber,
        permitDownload = permitDownload,
        principalOrFirstMaker = principalOrFirstMaker,
        productionPlaces = productionPlaces,
        showImage = showImage,
        title = title,
        webImage = webImageEntity.toWebImage()

    )
}

fun com.feature.common.domain.entity.art.HeaderImageEntity.toHeaderImage(): HeaderImage {
    return HeaderImage(
        guid = guid,
        offsetPercentageX = offsetPercentageX,
        height = height,
        offsetPercentageY = offsetPercentageY,
        url = url,
        width = width
    )
}

fun com.feature.common.domain.entity.art.LinksEntity.toLinks(): Links {
    return Links(
        self = self,
        web = web
    )
}

fun com.feature.common.domain.entity.art.WebImageEntity.toWebImage(): WebImage {
    return WebImage(
        guid = guid,
        height = height,
        offsetPercentageX = offsetPercentageX,
        offsetPercentageY = offsetPercentageY,
        url = url,
        width = width
    )
}

