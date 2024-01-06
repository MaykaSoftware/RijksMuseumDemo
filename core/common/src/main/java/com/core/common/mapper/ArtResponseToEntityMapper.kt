package com.core.common.mapper

import com.core.common.dto.home.ArtObjectResponse
import com.core.common.dto.home.HeaderImageResponse
import com.core.common.dto.home.LinksResponse
import com.core.common.dto.home.WebImageResponse
import com.core.common.entity.art.ArtObjectEntity
import com.core.common.entity.art.HeaderImageEntity
import com.core.common.entity.art.LinksEntity
import com.core.common.entity.art.WebImageEntity

fun ArtObjectResponse.toArtObjectEntity(): ArtObjectEntity {
    return ArtObjectEntity(
        hasImage = hasImage,
        headerImageEntity = headerImageResponse.toHeaderImageEntity(),
        id = id,
        linksEntity = linksResponse.toLinksEntity(),
        longTitle = longTitle,
        objectNumber = objectNumber,
        permitDownload = permitDownload,
        principalOrFirstMaker = principalOrFirstMaker,
        productionPlaces = productionPlaces,
        showImage = showImage,
        title = title,
        webImageEntity = webImageResponse.toWebImageEntity(),
        page = 0

    )
}

fun HeaderImageResponse.toHeaderImageEntity(): HeaderImageEntity {
    return HeaderImageEntity(
        guid = guid,
        offsetPercentageX = offsetPercentageX,
        height = height,
        offsetPercentageY = offsetPercentageY,
        url = url,
        width = width
    )
}

fun LinksResponse.toLinksEntity(): LinksEntity {
    return LinksEntity(
        self = self,
        web = web
    )
}

fun WebImageResponse.toWebImageEntity(): WebImageEntity {
    return WebImageEntity(
        guid = guid,
        height = height,
        offsetPercentageX = offsetPercentageX,
        offsetPercentageY = offsetPercentageY,
        url = url,
        width = width
    )
}

