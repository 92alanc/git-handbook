package com.braincorp.githandbook.commands.data.mapping

import com.braincorp.githandbook.commands.data.model.DbCommand
import com.braincorp.githandbook.commands.data.model.DbDescription
import com.braincorp.githandbook.commands.data.model.DbParameter
import com.braincorp.githandbook.commands.domain.model.Command

interface DataToDomainMapper {

    fun map(map: Map<DbCommand, Map<DbParameter, DbDescription>>): List<Command>
}
