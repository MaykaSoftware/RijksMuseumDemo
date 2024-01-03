package com.feature.common.domain.entity.auth

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.feature.common.domain.model.auth.User

@Entity
data class UserEntity(
    @PrimaryKey
    val userID: Int,
    val username: String,
    val email: String,
    val name: String
)

fun User.toUserEntity(): UserEntity {
    return UserEntity(
        userID = userID,
        username = username,
        email = email,
        name = name
    )
}