package com.core.cache.dataSource.database

import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class ArtDetailTypeConverter {
    @TypeConverter
    fun convertWebImageEntityToJsonString(webImageDetailEntity: com.feature.common.domain.entity.art_detail.WebImageDetailEntity?): String =
        Json.encodeToString(webImageDetailEntity)


    @TypeConverter
    fun convertWebImageEntityToObject(json: String): com.feature.common.domain.entity.art_detail.WebImageDetailEntity? =
        Json.decodeFromString(json)


}