package com.feature.art.data.dataSource.local

import androidx.paging.PagingSource
import com.core.cache.dataSource.dao.ArtObjectDao
import com.core.cache.dataSource.database.ArtDatabase
import com.feature.common.domain.entity.art.ArtObjectEntity
import javax.inject.Inject

class ArtObjectDaoImpl @Inject constructor(private val database: ArtDatabase): ArtObjectDao {
    override suspend fun upsertAll(artObjectEntity: List<ArtObjectEntity>) {
        database.artObjectDao().upsertAll(artObjectEntity)
    }

    override fun pagingSource(): PagingSource<Int, ArtObjectEntity> {
        return database.artObjectDao().pagingSource()
    }

    override fun deleteAll() {
        database.artObjectDao().deleteAll()
    }
}