package com.braincorp.githandbook.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.braincorp.githandbook.repository.CommandRepository
import com.braincorp.githandbook.repository.CommandRepositoryImpl

class CommandViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: CommandRepository = CommandRepositoryImpl(application)

    suspend fun getCommandsAsync() = repository.getCommandsAsync()

}