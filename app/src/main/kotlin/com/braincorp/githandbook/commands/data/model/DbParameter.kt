package com.braincorp.githandbook.commands.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Parameter")
data class DbParameter(
    @PrimaryKey val id: Int,
    val token: Int?,
    @ColumnInfo(name = "command_id") val commandId: Int,
    @ColumnInfo(name = "description_id") val descriptionId: Int
)
