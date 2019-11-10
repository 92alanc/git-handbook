package com.braincorp.githandbook.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.braincorp.githandbook.R
import kotlinx.android.synthetic.main.activity_command.*

class CommandActivity : AppCompatActivity(R.layout.activity_command) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configureToolbar()
    }

    private fun configureToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = intent.getStringExtra(EXTRA_COMMAND_NAME)
    }

    companion object {
        private const val EXTRA_COMMAND_NAME = "command_name"

        fun newIntent(context: Context, commandName: String): Intent {
            return Intent(context, CommandActivity::class.java)
                    .putExtra(EXTRA_COMMAND_NAME, commandName)
        }
    }

}