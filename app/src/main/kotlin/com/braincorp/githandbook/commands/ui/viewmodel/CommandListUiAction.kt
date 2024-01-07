package com.braincorp.githandbook.commands.ui.viewmodel

import com.braincorp.githandbook.commands.ui.model.UiCommand

sealed class CommandListUiAction {

    data class ShowCommandDetails(val command: UiCommand) : CommandListUiAction()
}
