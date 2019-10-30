package com.braincorp.githandbook.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.braincorp.githandbook.model.Command

@Database(entities = [Command::class], version = 1, exportSchema = false)
abstract class CommandDatabase : RoomDatabase() {

    abstract fun commandDao(): CommandDao

    companion object {
        @JvmStatic
        @Synchronized
        fun getInstance(context: Context) = lazy {
            Room.databaseBuilder(context, CommandDatabase::class.java, "commands.db")
                    .createFromAsset("database.db")
                    .fallbackToDestructiveMigration()
                    .build()
        }.value
    }

}