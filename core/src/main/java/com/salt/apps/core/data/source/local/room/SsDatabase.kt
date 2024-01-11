package com.salt.apps.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.salt.apps.core.data.source.local.entity.UserEntity
import com.salt.apps.core.data.source.local.room.dao.UserDao
import com.salt.apps.core.utilities.Constants.DB_VERSION

@Database(
    entities = [UserEntity::class],
    version = DB_VERSION,
    exportSchema = false
)

abstract class SsDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}