package com.braincorp.githandbook.viewmodel

import androidx.lifecycle.ViewModel
import com.braincorp.githandbook.repository.CommandRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CommandViewModel @Inject constructor(
    private val repository: CommandRepository
) : ViewModel() {

    suspend fun getCommandsAsync() = repository.getCommandsAsync()
}