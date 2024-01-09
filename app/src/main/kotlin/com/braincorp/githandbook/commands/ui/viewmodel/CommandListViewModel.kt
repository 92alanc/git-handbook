package com.braincorp.githandbook.commands.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.braincorp.githandbook.commands.domain.repository.CommandRepository
import com.braincorp.githandbook.commands.ui.mapping.toUi
import com.braincorp.githandbook.commands.ui.model.UiCommand
import com.braincorp.githandbook.core.di.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "LOG_ALAN"

@HiltViewModel
class CommandListViewModel @Inject constructor(
    private val repository: CommandRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _state = MutableStateFlow(CommandListUiState())
    private val _action = MutableSharedFlow<CommandListUiAction>()

    val state = _state.asStateFlow()
    val action = _action.asSharedFlow()

    fun getAllCommands() {
        viewModelScope.launch(dispatcher) {
            repository.getAllCommands().onStart {
                _state.update { it.onLoading() }
            }.onCompletion {
                _state.update { it.onFinishedLoading() }
            }.catch {
                Log.e(TAG, "Error getting commands", it)
            }.collect { commands ->
                val uiCommands = commands.map {
                    it.toUi()
                }

                _state.update { it.onCommandsReceived(uiCommands) }
            }
        }
    }

    fun onCommandClicked(command: UiCommand) {
        val action = CommandListUiAction.ShowCommandDetails(command)
        sendAction(action)
    }

    fun onReferenceDialogueButtonClicked() {
        val url = "https://git-scm.com/documentation"
        val action = CommandListUiAction.ViewWebPage(url)
        sendAction(action)
    }

    private fun sendAction(action: CommandListUiAction) {
        viewModelScope.launch(dispatcher) {
            _action.emit(action)
        }
    }
}