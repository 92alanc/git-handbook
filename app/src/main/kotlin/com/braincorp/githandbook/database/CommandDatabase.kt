package com.braincorp.githandbook.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.braincorp.githandbook.model.Command

@Database(entities = [Command::class], version = 1, exportSchema = false)
abstract class CommandDatabase : RoomDatabase() {

    abstract fun commandDao(): CommandDao

}