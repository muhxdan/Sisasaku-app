package com.salt.apps.core.utilities

import com.salt.apps.core.data.source.local.entity.UserEntity
import com.salt.apps.core.data.source.remote.response.UserResponse
import com.salt.apps.core.domain.model.User


object DataMapper {
    fun mapUserResponseToEntity(userId: String, userResponse: UserResponse): UserEntity =
        UserEntity(
            id = userId,
            name = userResponse.name,
            email = userResponse.email,
            imageUrl = userResponse.avatarUrl
        )

    fun mapUserEntityToUserModel(input: UserEntity): User =
        input.let {
            User(
                id = it.id,
                name = it.name,
                email = it.email,
                imageUrl = it.imageUrl
            )
        }
}