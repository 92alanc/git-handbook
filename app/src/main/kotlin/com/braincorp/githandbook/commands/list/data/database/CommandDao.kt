package com.braincorp.githandbook.commands.list.data.database

import androidx.room.Dao
import androidx.room.Query
import com.braincorp.githandbook.commands.list.data.model.DbCommand
import com.braincorp.githandbook.commands.list.data.model.DbDescription
import com.braincorp.githandbook.commands.list.data.model.DbParameter

@Dao
interface CommandDao {

    @Query("SELECT * FROM Command ORDER BY name")
    suspend fun getAllCommands(): List<DbCommand>

    @Query("SELECT * FROM Parameter WHERE command_id = :commandId")
    suspend fun getParametersByCommandId(commandId: Int): List<DbParameter>

    @Query("SELECT * FROM Description WHERE id = :id")
    suspend fun getDescriptionById(id: Int): DbDescription
}
