package com.salt.apps.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.salt.apps.core.data.source.local.entity.Entity
import com.salt.apps.core.utilities.Constants.DB_VERSION

@Database(
    entities = [Entity::class],
    version = DB_VERSION,
    exportSchema = false
)

abstract class SsDatabase : RoomDatabase() {
    abstract fun ssDao(): SsDao
}