package com.farid.rijksmuseumdemo.data.mapper

import com.farid.rijksmuseumdemo.data.local.entity.home.ArtObjectEntity
import com.farid.rijksmuseumdemo.data.local.entity.home.HeaderImageEntity
import com.farid.rijksmuseumdemo.data.local.entity.home.LinksEntity
import com.farid.rijksmuseumdemo.data.local.entity.home.WebImageEntity
import com.farid.rijksmuseumdemo.data.remote.dto.home.ArtObjectResponse
import com.farid.rijksmuseumdemo.data.remote.dto.home.HeaderImageResponse
import com.farid.rijksmuseumdemo.data.remote.dto.home.LinksResponse
import com.farid.rijksmuseumdemo.data.remote.dto.home.WebImageResponse


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

