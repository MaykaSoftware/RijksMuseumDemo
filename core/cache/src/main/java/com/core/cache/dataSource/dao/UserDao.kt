package com.core.cache.dataSource.dao

import androidx.room.Dao
import androidx.room.Upsert
import com.core.common.entity.auth.UserEntity

@Dao
interface UserDao {
    @Upsert
    suspend fun upsert(user: UserEntity)
}