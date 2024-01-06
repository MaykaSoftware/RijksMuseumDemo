package com.core.cache.dataSource.database

import androidx.room.TypeConverter
import com.core.common.entity.art.HeaderImageEntity
import com.core.common.entity.art.LinksEntity
import com.core.common.entity.art.WebImageEntity
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class ArtTypeConverter {

    @TypeConverter
    fun convertHeaderImageToJsonString(headerImageEntity: HeaderImageEntity?): String =
        Json.encodeToString(headerImageEntity)


    @TypeConverter
    fun convertHeaderImageToObject(json: String): HeaderImageEntity? =
        Json.decodeFromString(json)


    @TypeConverter
    fun convertLinksToJsonString(linksEntity: LinksEntity?): String =
        Json.encodeToString(linksEntity)


    @TypeConverter
    fun convertLinksToObject(json: String): LinksEntity? =
        Json.decodeFromString(json)


    @TypeConverter
    fun convertWebImageEntityToJsonString(webImageEntity: WebImageEntity?): String =
        Json.encodeToString(webImageEntity)


    @TypeConverter
    fun convertWebImageEntityToObject(json: String): WebImageEntity? =
        Json.decodeFromString(json)


    @TypeConverter
    fun convertProductionPlacesToJsonString(productionPlaces: List<String>): String =
        Json.encodeToString(productionPlaces)

    @TypeConverter
    fun convertProductionPlacesToList(json: String): List<String> = Json.decodeFromString(json)

}