package com.braincorp.githandbook.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.braincorp.githandbook.repository.CommandRepository

class CommandViewModel(
        application: Application,
        private val repository: CommandRepository
) : AndroidViewModel(application) {

    suspend fun getCommandsAsync() = repository.getCommandsAsync()

    suspend fun getCommandsByNameAsync(name: String) = repository.getCommandsByNameAsync(name)

}