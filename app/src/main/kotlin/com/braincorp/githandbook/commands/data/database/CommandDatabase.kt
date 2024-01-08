package com.braincorp.githandbook.commands.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.braincorp.githandbook.commands.data.model.DbCommand
import com.braincorp.githandbook.commands.data.model.DbDescription
import com.braincorp.githandbook.commands.data.model.DbParameter

@Database(
    entities = [
        DbCommand::class,
        DbParameter::class,
        DbDescription::class
    ],
    version = 2,
    exportSchema = false
)
abstract class CommandDatabase : RoomDatabase() {

    abstract fun commandDao(): CommandDao
}
