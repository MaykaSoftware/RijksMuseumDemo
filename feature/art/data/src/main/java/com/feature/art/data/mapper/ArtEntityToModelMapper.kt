package com.feature.art.data.mapper

import com.core.cache.entity.art.ArtObjectEntity
import com.core.cache.entity.art.HeaderImageEntity
import com.core.cache.entity.art.LinksEntity
import com.core.cache.entity.art.WebImageEntity
import com.feature.art.domain.model.ArtObject
import com.feature.art.domain.model.HeaderImage
import com.feature.art.domain.model.Links
import com.feature.art.domain.model.WebImage


fun ArtObjectEntity.toArtObject(): ArtObject {
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

fun HeaderImageEntity.toHeaderImage(): HeaderImage {
    return HeaderImage(
        guid = guid,
        offsetPercentageX = offsetPercentageX,
        height = height,
        offsetPercentageY = offsetPercentageY,
        url = url,
        width = width
    )
}

fun LinksEntity.toLinks(): Links {
    return Links(
        self = self,
        web = web
    )
}

fun WebImageEntity.toWebImage(): WebImage {
    return WebImage(
        guid = guid,
        height = height,
        offsetPercentageX = offsetPercentageX,
        offsetPercentageY = offsetPercentageY,
        url = url,
        width = width
    )
}

