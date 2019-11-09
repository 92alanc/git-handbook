package com.braincorp.githandbook.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.braincorp.githandbook.database.CommandDao
import com.braincorp.githandbook.database.CommandDatabase
import com.braincorp.githandbook.model.Command
import com.braincorp.githandbook.util.getString
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class CommandRepositoryImpl(private val context: Context) : CommandRepository {

    private val database: CommandDao = CommandDatabase.getInstance(context).commandDao()

    override suspend fun getCommandsAsync() = withContext(Dispatchers.IO) {
        async {
            MutableLiveData<List<Command>>().apply {
                value = database.select().map {
                    val dbParameter = it.parameter
                    val parameter = if (dbParameter != null)
                        context.getString(dbParameter)
                    else
                        null
                    val description = context.getString(it.description)
                    val example = context.getString(it.example)

                    it.copy(parameter = parameter, description = description, example = example)
                }
            }
        }
    }

}