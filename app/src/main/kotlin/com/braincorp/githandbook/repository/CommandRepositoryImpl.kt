package com.braincorp.githandbook.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.braincorp.githandbook.database.CommandDao
import com.braincorp.githandbook.database.CommandDatabase
import com.braincorp.githandbook.model.Command
import com.braincorp.githandbook.util.getString
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class CommandRepositoryImpl(private val context: Context) : CommandRepository {

    private val database = buildDatabase()

    override suspend fun getCommandsAsync() = withContext(Dispatchers.IO) {
        async {
            MutableLiveData<List<Command>>().apply {
                val commands = database.select().map(::buildCommandFromDb)
                postValue(commands)
            }
        }
    }

    override suspend fun getCommandsByNameAsync(name: String) = withContext(Dispatchers.IO) {
        async {
            MutableLiveData<List<Command>>().apply {
                val commands = database.select(name).map(::buildCommandFromDb)
                postValue(commands)
            }
        }
    }

    private fun buildDatabase(): CommandDao {
        return Room.databaseBuilder(context, CommandDatabase::class.java, "commands-db")
                .createFromAsset("database.db")
                .fallbackToDestructiveMigration()
                .build()
                .commandDao()
    }

    private fun buildCommandFromDb(dbCommand: Command): Command {
        val parameter = context.getString(dbCommand.parameter)
        val description = context.getString(dbCommand.description) ?: ""
        val example = context.getString(dbCommand.example) ?: ""

        return dbCommand.copy(parameter = parameter, description = description, example = example)
    }

}