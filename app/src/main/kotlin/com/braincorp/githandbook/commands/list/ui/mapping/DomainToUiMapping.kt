package com.braincorp.githandbook.commands.list.ui.mapping

import com.braincorp.githandbook.commands.list.domain.model.Command
import com.braincorp.githandbook.commands.list.domain.model.Parameter
import com.braincorp.githandbook.commands.list.ui.model.UiCommand
import com.braincorp.githandbook.commands.list.ui.model.UiParameter

fun Command.toUi() = UiCommand(
    id = id,
    name = name,
    parameters = parameters.map { it.toUi() }
)

private fun Parameter.toUi() = UiParameter(
    name = name,
    description = description,
    example = example
)
