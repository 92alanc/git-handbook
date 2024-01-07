package com.braincorp.githandbook.commands.data.mapping

import com.braincorp.githandbook.commands.data.model.DbCommand
import com.braincorp.githandbook.commands.domain.model.Command

interface DataToDomainMapper {

    fun map(dbCommands: List<DbCommand>): List<Command>
}
