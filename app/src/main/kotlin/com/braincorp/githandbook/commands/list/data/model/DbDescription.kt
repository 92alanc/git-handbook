package com.braincorp.githandbook.commands.list.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Description")
data class DbDescription(
    @PrimaryKey val id: Int,
    val token: Int
)
