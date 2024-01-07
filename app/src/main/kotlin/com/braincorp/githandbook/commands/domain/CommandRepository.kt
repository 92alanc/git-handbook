package com.braincorp.githandbook.commands.domain

import androidx.lifecycle.LiveData
import com.braincorp.githandbook.commands.data.model.Command
import kotlinx.coroutines.Deferred

interface CommandRepository {

    suspend fun getCommandsAsync(): Deferred<LiveData<List<Command>>>
}