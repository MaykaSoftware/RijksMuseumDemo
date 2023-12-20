package com.farid.rijksmuseumdemo.data.local.entity.home

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_key")
data class RemoteKeysEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "art_object_id")
    val artObjectID: String,
    val prevKey: Int?,
    val currentPage: Int,
    val nextKey: Int?,
    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis()
)