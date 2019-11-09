package com.braincorp.githandbook.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.braincorp.githandbook.model.Command
import com.braincorp.githandbook.repository.CommandRepository

class CommandViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CommandRepository(application)

    fun getCommands(): LiveData<List<Command>> = repository.getCommands()

}