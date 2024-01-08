package com.braincorp.githandbook.commands.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Command")
data class DbCommand(
    @PrimaryKey val id: Int,
    val name: String
)
