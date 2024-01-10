package com.braincorp.githandbook.commands.list.ui.viewmodel

import com.braincorp.githandbook.commands.list.ui.model.UiCommand

data class CommandListUiState(
    val isLoading: Boolean = false,
    val commands: List<UiCommand>? = null
) {

    fun onLoading() = copy(isLoading = true, commands = null)

    fun onFinishedLoading() = copy(isLoading = false)

    fun onCommandsReceived(commands: List<UiCommand>) = copy(commands = commands)
}
