package com.braincorp.githandbook.commands.data.database

import androidx.room.Dao
import androidx.room.Query
import com.braincorp.githandbook.commands.data.model.DbCommand

@Dao
interface CommandDao {

    @Query("SELECT * FROM DbCommand ORDER BY name")
    suspend fun getAllCommands(): List<DbCommand>
}
