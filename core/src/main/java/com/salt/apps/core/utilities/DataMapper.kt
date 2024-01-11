package com.salt.apps.core.utilities

import com.salt.apps.core.data.source.local.entity.UserEntity
import com.salt.apps.core.data.source.remote.response.UserResponse
import com.salt.apps.core.domain.model.User


object DataMapper {
    fun mapUserResponseToEntity(input: UserResponse): UserEntity =
        UserEntity(
            id = input.iss,
            name = input.name,
            email = input.email,
            imageUrl = input.avatarUrl
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