package com.braincorp.githandbook.repository

import androidx.lifecycle.LiveData
import com.braincorp.githandbook.model.Command
import kotlinx.coroutines.Deferred

interface CommandRepository {
    suspend fun getCommandsAsync(): Deferred<LiveData<List<Command>>>
    suspend fun getCommandsByNameAsync(name: String): Deferred<LiveData<List<Command>>>
}