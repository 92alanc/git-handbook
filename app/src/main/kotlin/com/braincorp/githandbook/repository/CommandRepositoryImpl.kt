package com.braincorp.githandbook.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.braincorp.githandbook.database.CommandDao
import com.braincorp.githandbook.model.Command
import com.braincorp.githandbook.util.getString
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class CommandRepositoryImpl(
    private val context: Context,
    private val database: CommandDao
) : CommandRepository {

    override suspend fun getCommandsAsync() = withContext(Dispatchers.IO) {
        async {
            MutableLiveData<List<Command>>().apply {
                val commands = database.select().map(::buildCommandFromDb)
                postValue(commands)
            }
        }
    }

    private fun buildCommandFromDb(dbCommand: Command): Command {
        val parameter = context.getString(dbCommand.parameter)
        val description = context.getString(dbCommand.description) ?: ""
        val example = context.getString(dbCommand.example) ?: ""

        return dbCommand.copy(parameter = parameter, description = description, example = example)
    }

}