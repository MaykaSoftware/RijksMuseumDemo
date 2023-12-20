package com.farid.rijksmuseumdemo.data.local.database

import androidx.room.TypeConverter
import com.farid.rijksmuseumdemo.data.local.entity.home.HeaderImageEntity
import com.farid.rijksmuseumdemo.data.local.entity.home.LinksEntity
import com.farid.rijksmuseumdemo.data.local.entity.home.WebImageEntity
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
    fun convertLinksToObject(json: String): LinksEntity? = Json.decodeFromString(json)


    @TypeConverter
    fun convertWebImageEntityToJsonString(webImageEntity: WebImageEntity?): String =
        Json.encodeToString(webImageEntity)


    @TypeConverter
    fun convertWebImageEntityToObject(json: String): WebImageEntity? = Json.decodeFromString(json)


    @TypeConverter
    fun convertProductionPlacesToJsonString(productionPlaces: List<String>): String =
        Json.encodeToString(productionPlaces)

    @TypeConverter
    fun convertProductionPlacesToList(json: String): List<String> = Json.decodeFromString(json)

}