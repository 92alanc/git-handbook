package com.braincorp.githandbook.commands.details.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.braincorp.githandbook.commands.list.ui.model.UiCommand
import com.braincorp.githandbook.commands.list.ui.model.UiParameter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CommandDetailsViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(CommandDetailsUiState())

    val state = _state.asStateFlow()

    fun start(command: UiCommand) {
        _state.update { it.onCommandReceived(command) }
        _state.update { it.onParameterChanged(command.parameters.first()) }
    }

    fun onParameterItemClicked(parameter: UiParameter) {
        _state.update { it.onParameterChanged(parameter) }
    }
}
