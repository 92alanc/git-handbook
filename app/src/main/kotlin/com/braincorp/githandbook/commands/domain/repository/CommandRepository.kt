package com.braincorp.githandbook.commands.domain.repository

import com.braincorp.githandbook.commands.domain.model.Command
import kotlinx.coroutines.flow.Flow

interface CommandRepository {

    fun getAllCommands(): Flow<List<Command>>
}