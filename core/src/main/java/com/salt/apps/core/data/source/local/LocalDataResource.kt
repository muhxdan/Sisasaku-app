package com.salt.apps.core.data.source.local

import com.salt.apps.core.data.source.local.entity.UserEntity
import com.salt.apps.core.data.source.local.room.dao.UserDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(
    private val userDao: UserDao
) {
    suspend fun insertUser(user: UserEntity) = userDao.insertUser(user)

    fun getUser() = userDao.getUser()

    suspend fun deleteUser() = userDao.deleteUser()
}