package com.core.common.mapper

import com.core.common.entity.art.ArtObjectEntity
import com.core.common.entity.art.HeaderImageEntity
import com.core.common.entity.art.LinksEntity
import com.core.common.entity.art.WebImageEntity
import com.core.common.model.art.ArtObject
import com.core.common.model.art.HeaderImage
import com.core.common.model.art.Links
import com.core.common.model.art.WebImage

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

