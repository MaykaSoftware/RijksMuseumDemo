package com.farid.rijksmuseumdemo.data.mapper

import com.farid.rijksmuseumdemo.data.local.entity.home.ArtObjectEntity
import com.farid.rijksmuseumdemo.data.local.entity.home.HeaderImageEntity
import com.farid.rijksmuseumdemo.data.local.entity.home.LinksEntity
import com.farid.rijksmuseumdemo.data.local.entity.home.WebImageEntity
import com.farid.rijksmuseumdemo.domain.model.home.ArtObject
import com.farid.rijksmuseumdemo.domain.model.home.HeaderImage
import com.farid.rijksmuseumdemo.domain.model.home.Links
import com.farid.rijksmuseumdemo.domain.model.home.WebImage


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

