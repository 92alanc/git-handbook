package com.braincorp.githandbook.commands.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.braincorp.githandbook.commands.data.model.DbCommand

@Database(entities = [DbCommand::class], version = 1, exportSchema = false)
abstract class CommandDatabase : RoomDatabase() {

    abstract fun commandDao(): CommandDao

}