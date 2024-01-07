package com.braincorp.githandbook.commands.data.mapping

import android.content.Context
import com.braincorp.githandbook.commands.data.model.DbCommand
import com.braincorp.githandbook.commands.domain.model.Command
import com.braincorp.githandbook.commands.domain.model.Parameter
import com.braincorp.githandbook.core.util.getStringFromResourceName
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DataToDomainMapperImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : DataToDomainMapper {

    override fun map(dbCommands: List<DbCommand>): List<Command> {
        val groupedParameters = dbCommands.groupBy { dbCommand ->
            dbCommand.name
        }.map { (_, commands) ->
            commands.map {
                Parameter(
                    name = context.getStringFromResourceName(it.parameter),
                    description = context.getStringFromResourceName(it.description).orEmpty(),
                    example = context.getStringFromResourceName(it.example).orEmpty()
                )
            }
        }

        return dbCommands.distinctBy { it.name }.mapIndexed { index, dbCommand ->
            val parameters = groupedParameters[index]
            Command(
                id = dbCommand.id,
                name = dbCommand.name,
                parameters = parameters
            )
        }
    }
}
