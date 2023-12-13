package com.salt.apps.core.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "initial")
data class Entity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
)