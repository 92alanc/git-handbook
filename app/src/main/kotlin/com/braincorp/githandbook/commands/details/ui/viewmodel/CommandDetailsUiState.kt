package com.braincorp.githandbook.commands.details.ui.viewmodel

import com.braincorp.githandbook.commands.list.ui.model.UiCommand
import com.braincorp.githandbook.commands.list.ui.model.UiParameter

data class CommandDetailsUiState(
    val description: String? = null,
    val example: String? = null,
    val title: String? = null
) {

    fun onCommandReceived(command: UiCommand) = copy(title = command.name)

    fun onParameterChanged(parameter: UiParameter) = copy(
        description = parameter.description,
        example = parameter.example
    )
}
