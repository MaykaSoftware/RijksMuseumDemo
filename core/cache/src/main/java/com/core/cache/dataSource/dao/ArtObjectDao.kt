package com.core.cache.dataSource.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface ArtObjectDao {

    @Upsert
    suspend fun upsertAll(artObjectEntity: List<com.feature.common.domain.entity.art.ArtObjectEntity>)

    @Query("SELECT * FROM art_object")
    fun pagingSource(): PagingSource<Int, com.feature.common.domain.entity.art.ArtObjectEntity>

    @Query("DELETE  FROM art_object")
    fun deleteAll()
}