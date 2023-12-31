package com.farid.rijksmuseumdemo.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.farid.rijksmuseumdemo.data.local.entity.detail.ArtObjectDetailEntity
import com.farid.rijksmuseumdemo.data.local.entity.home.ArtObjectEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ArtObjectDao {

    @Upsert
    suspend fun upsertAll(artObjectEntity: List<ArtObjectEntity>)

    @Query("SELECT * FROM art_object")
    fun pagingSource(): PagingSource<Int, ArtObjectEntity>

    @Query("DELETE  FROM art_object")
    fun deleteAll()

    @Query("SELECT * FROM art_detail_object WHERE objectNumber = :objectNumber")
    fun getArtObjectFlowById(objectNumber: String): Flow<ArtObjectDetailEntity>

    @Query("SELECT * FROM art_detail_object WHERE objectNumber = :objectNumber")
    suspend fun getArtObjectById(objectNumber: String): ArtObjectDetailEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArtObject(artObjectEntity: ArtObjectDetailEntity)
}