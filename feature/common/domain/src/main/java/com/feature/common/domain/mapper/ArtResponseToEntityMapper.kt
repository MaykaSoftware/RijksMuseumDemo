package com.feature.common.domain.mapper

import com.farid.rijksmuseumdemo.data.remote.dto.home.ArtObjectResponse
import com.farid.rijksmuseumdemo.data.remote.dto.home.HeaderImageResponse
import com.farid.rijksmuseumdemo.data.remote.dto.home.LinksResponse
import com.farid.rijksmuseumdemo.data.remote.dto.home.WebImageResponse


fun ArtObjectResponse.toArtObjectEntity(): com.feature.common.domain.entity.art.ArtObjectEntity {
    return com.feature.common.domain.entity.art.ArtObjectEntity(
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

fun HeaderImageResponse.toHeaderImageEntity(): com.feature.common.domain.entity.art.HeaderImageEntity {
    return com.feature.common.domain.entity.art.HeaderImageEntity(
        guid = guid,
        offsetPercentageX = offsetPercentageX,
        height = height,
        offsetPercentageY = offsetPercentageY,
        url = url,
        width = width
    )
}

fun LinksResponse.toLinksEntity(): com.feature.common.domain.entity.art.LinksEntity {
    return com.feature.common.domain.entity.art.LinksEntity(
        self = self,
        web = web
    )
}

fun WebImageResponse.toWebImageEntity(): com.feature.common.domain.entity.art.WebImageEntity {
    return com.feature.common.domain.entity.art.WebImageEntity(
        guid = guid,
        height = height,
        offsetPercentageX = offsetPercentageX,
        offsetPercentageY = offsetPercentageY,
        url = url,
        width = width
    )
}

