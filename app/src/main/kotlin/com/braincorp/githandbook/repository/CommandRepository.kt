package com.braincorp.githandbook.repository

import android.content.Context
import com.braincorp.githandbook.database.CommandDatabase

class CommandRepository(context: Context) {

    private val database = CommandDatabase.getInstance(context).commandDao()

}