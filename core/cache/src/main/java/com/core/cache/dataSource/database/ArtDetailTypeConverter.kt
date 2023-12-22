package com.core.cache.dataSource.database

import androidx.room.TypeConverter
import com.core.cache.entity.art_detail.WebImageDetailEntity
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class ArtDetailTypeConverter {
    @TypeConverter
    fun convertWebImageEntityToJsonString(webImageDetailEntity: WebImageDetailEntity?): String =
        Json.encodeToString(webImageDetailEntity)


    @TypeConverter
    fun convertWebImageEntityToObject(json: String): WebImageDetailEntity? =
        Json.decodeFromString(json)


}