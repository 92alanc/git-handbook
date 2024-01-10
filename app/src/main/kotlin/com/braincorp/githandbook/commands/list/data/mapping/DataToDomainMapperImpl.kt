package com.braincorp.githandbook.commands.list.data.mapping

import android.content.Context
import com.braincorp.githandbook.commands.list.data.model.DbCommand
import com.braincorp.githandbook.commands.list.data.model.DbDescription
import com.braincorp.githandbook.commands.list.data.model.DbParameter
import com.braincorp.githandbook.commands.list.domain.model.Command
import com.braincorp.githandbook.commands.list.domain.model.Parameter
import com.braincorp.githandbook.core.util.getStringFromResourceName
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DataToDomainMapperImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : DataToDomainMapper {

    override fun map(map: Map<DbCommand, Map<DbParameter, DbDescription>>): List<Command> {
        return map.map { (command, paramDescriptionMap) ->
            val parameters = paramDescriptionMap.map { (parameter, description) ->
                Parameter(
                    name = context.getStringFromResourceName("param_${parameter.token}"),
                    description = context.getStringFromResourceName("content_${description.token}").orEmpty(),
                    example = context.getStringFromResourceName("example_${description.token}").orEmpty()
                )
            }

            Command(
                id = command.id,
                name = command.name,
                parameters = parameters
            )
        }
    }
}
