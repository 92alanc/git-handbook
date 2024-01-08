package com.braincorp.githandbook.commands.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Description")
data class DbDescription(
    @PrimaryKey val id: Int,
    val token: Int
)
