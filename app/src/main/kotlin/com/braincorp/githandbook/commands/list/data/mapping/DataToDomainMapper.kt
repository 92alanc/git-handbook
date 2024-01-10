package com.braincorp.githandbook.commands.list.data.mapping

import com.braincorp.githandbook.commands.list.data.model.DbCommand
import com.braincorp.githandbook.commands.list.data.model.DbDescription
import com.braincorp.githandbook.commands.list.data.model.DbParameter
import com.braincorp.githandbook.commands.list.domain.model.Command

interface DataToDomainMapper {

    fun map(map: Map<DbCommand, Map<DbParameter, DbDescription>>): List<Command>
}
