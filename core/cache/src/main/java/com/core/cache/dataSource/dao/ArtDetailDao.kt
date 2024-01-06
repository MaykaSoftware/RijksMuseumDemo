package com.core.cache.dataSource.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.core.common.entity.art_detail.ArtObjectDetailEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ArtDetailDao {

    @Query("SELECT * FROM art_detail_object WHERE objectNumber = :objectNumber")
    fun getArtObjectFlowById(objectNumber: String): Flow<ArtObjectDetailEntity>

    @Query("SELECT * FROM art_detail_object WHERE objectNumber = :objectNumber")
    suspend fun getArtObjectById(objectNumber: String): ArtObjectDetailEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArtObject(artObjectEntity: ArtObjectDetailEntity)
}