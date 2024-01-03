package com.core.cache.dataSource.dao

import androidx.room.Dao
import androidx.room.Upsert
import com.feature.common.domain.entity.auth.UserEntity

@Dao
interface UserDao {
    @Upsert
    suspend fun upsert(user: UserEntity)
}