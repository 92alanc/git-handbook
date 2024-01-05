package com.braincorp.githandbook.viewmodel

import androidx.lifecycle.ViewModel
import com.braincorp.githandbook.repository.CommandRepository

class CommandViewModel(private val repository: CommandRepository) : ViewModel() {

    suspend fun getCommandsAsync() = repository.getCommandsAsync()
}