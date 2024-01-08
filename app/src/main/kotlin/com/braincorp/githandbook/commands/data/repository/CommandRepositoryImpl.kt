package com.braincorp.githandbook.commands.data.repository

import com.braincorp.githandbook.commands.data.database.CommandDao
import com.braincorp.githandbook.commands.data.mapping.DataToDomainMapper
import com.braincorp.githandbook.commands.domain.model.Command
import com.braincorp.githandbook.commands.domain.repository.CommandRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CommandRepositoryImpl @Inject constructor(
    private val mapper: DataToDomainMapper,
    private val database: CommandDao
) : CommandRepository {

    override fun getAllCommands(): Flow<List<Command>> = flow {
        val map = database.getAllCommands().associateWith { command ->
            database.getParametersByCommandId(command.id).associateWith { parameter ->
                database.getDescriptionById(parameter.descriptionId)
            }
        }
        val commands = mapper.map(map)
        emit(commands)
    }
}
