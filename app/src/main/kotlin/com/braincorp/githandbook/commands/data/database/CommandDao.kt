package com.braincorp.githandbook.commands.data.database

import androidx.room.Dao
import androidx.room.Query
import com.braincorp.githandbook.commands.data.model.Command

@Dao
interface CommandDao {

    @Query("SELECT * FROM Command ORDER BY name")
    fun select(): List<Command>
}
