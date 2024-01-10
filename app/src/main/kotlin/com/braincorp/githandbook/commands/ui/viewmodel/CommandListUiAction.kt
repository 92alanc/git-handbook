package com.braincorp.githandbook.commands.ui.viewmodel

import com.braincorp.githandbook.commands.ui.model.UiCommand

sealed class CommandListUiAction {

    data class ShowCommandDetails(val command: UiCommand) : CommandListUiAction()

    object ShowReference : CommandListUiAction()

    object ShowPrivacyPolicy : CommandListUiAction()

    object ShowAppInfo : CommandListUiAction()

    object ShowPrivacySettings : CommandListUiAction()

    data class ViewWebPage(val url: String) : CommandListUiAction()
}
