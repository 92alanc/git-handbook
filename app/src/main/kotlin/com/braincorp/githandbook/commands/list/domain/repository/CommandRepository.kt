package com.braincorp.githandbook.commands.list.domain.repository

import com.braincorp.githandbook.commands.list.domain.model.Command
import kotlinx.coroutines.flow.Flow

interface CommandRepository {

    fun getAllCommands(): Flow<List<Command>>
}