package com.feature.authentication.data.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.core.cache.dataSource.dao.UserDao
import com.core.cache.di.PreferencesKeys
import com.feature.common.domain.entity.auth.UserEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthCacheDataSource @Inject constructor(
    private val dataStore: DataStore<Preferences>,
    private val userDao: UserDao
) {
    suspend fun upsert(user: UserEntity) {
        userDao.upsert(user)
    }
    suspend fun saveToken(token: String) {
        dataStore.edit { settings ->
            settings[PreferencesKeys.AUTH_TOKEN] = token
        }
    }

    fun readToken(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[PreferencesKeys.AUTH_TOKEN] ?: ""
        }
    }
}