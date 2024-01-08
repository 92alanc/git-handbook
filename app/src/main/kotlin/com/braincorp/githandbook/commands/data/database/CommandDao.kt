package com.braincorp.githandbook.commands.data.database

import androidx.room.Dao
import androidx.room.Query
import com.braincorp.githandbook.commands.data.model.DbCommand
import com.braincorp.githandbook.commands.data.model.DbDescription
import com.braincorp.githandbook.commands.data.model.DbParameter

@Dao
interface CommandDao {

    @Query("SELECT * FROM Command ORDER BY name")
    suspend fun getAllCommands(): List<DbCommand>

    @Query("SELECT * FROM Parameter WHERE command_id = :commandId")
    suspend fun getParametersByCommandId(commandId: Int): List<DbParameter>

    @Query("SELECT * FROM Description WHERE id = :id")
    suspend fun getDescriptionById(id: Int): DbDescription
}
