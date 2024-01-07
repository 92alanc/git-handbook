package com.braincorp.githandbook.commands.data.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.braincorp.githandbook.commands.domain.CommandRepository
import com.braincorp.githandbook.core.util.getStringFromResourceName
import com.braincorp.githandbook.commands.data.database.CommandDao
import com.braincorp.githandbook.commands.data.model.Command
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CommandRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
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
        val parameter = context.getStringFromResourceName(dbCommand.parameter)
        val description = context.getStringFromResourceName(dbCommand.description).orEmpty()
        val example = context.getStringFromResourceName(dbCommand.example).orEmpty()

        return dbCommand.copy(parameter = parameter, description = description, example = example)
    }

}