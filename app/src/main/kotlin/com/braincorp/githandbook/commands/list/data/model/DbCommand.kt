package com.braincorp.githandbook.commands.list.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Command")
data class DbCommand(
    @PrimaryKey val id: Int,
    val name: String
)
