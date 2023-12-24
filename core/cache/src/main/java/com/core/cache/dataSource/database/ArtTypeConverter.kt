package com.core.cache.dataSource.database

import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class ArtTypeConverter {

    @TypeConverter
    fun convertHeaderImageToJsonString(headerImageEntity: com.feature.common.domain.entity.art.HeaderImageEntity?): String =
        Json.encodeToString(headerImageEntity)


    @TypeConverter
    fun convertHeaderImageToObject(json: String): com.feature.common.domain.entity.art.HeaderImageEntity? =
        Json.decodeFromString(json)


    @TypeConverter
    fun convertLinksToJsonString(linksEntity: com.feature.common.domain.entity.art.LinksEntity?): String =
        Json.encodeToString(linksEntity)


    @TypeConverter
    fun convertLinksToObject(json: String): com.feature.common.domain.entity.art.LinksEntity? = Json.decodeFromString(json)


    @TypeConverter
    fun convertWebImageEntityToJsonString(webImageEntity: com.feature.common.domain.entity.art.WebImageEntity?): String =
        Json.encodeToString(webImageEntity)


    @TypeConverter
    fun convertWebImageEntityToObject(json: String): com.feature.common.domain.entity.art.WebImageEntity? = Json.decodeFromString(json)


    @TypeConverter
    fun convertProductionPlacesToJsonString(productionPlaces: List<String>): String =
        Json.encodeToString(productionPlaces)

    @TypeConverter
    fun convertProductionPlacesToList(json: String): List<String> = Json.decodeFromString(json)

}