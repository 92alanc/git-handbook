package com.braincorp.githandbook.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.braincorp.githandbook.R
import com.braincorp.githandbook.adapter.OnItemClickListener
import com.braincorp.githandbook.adapter.ParameterAdapter
import com.braincorp.githandbook.model.Command
import com.braincorp.githandbook.util.loadAnnoyingAds
import kotlinx.android.synthetic.main.activity_command_details.*

class CommandDetailsActivity : AppCompatActivity(R.layout.activity_command_details),
        OnItemClickListener {

    private val adapter = ParameterAdapter(onItemClickListener = this)

    private lateinit var commands: List<Command>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseIntent()
        configureToolbar()
        configureRecyclerView()
        configureTexts(commands.first())
        ad_view.loadAnnoyingAds()
    }

    override fun onItemClick(command: Command) {
        configureTexts(command)
    }

    @Suppress("UNCHECKED_CAST")
    private fun parseIntent() {
        intent.getParcelableArrayExtra(EXTRA_COMMANDS)?.let {
            commands = it.toList() as List<Command>
        }
    }

    private fun configureToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = commands.first().name
    }

    private fun configureRecyclerView() {
        recycler_view.adapter = adapter
        adapter.submitList(commands)
    }

    private fun configureTexts(command: Command) {
        txt_description.text = command.description
        txt_example.text = command.example
    }

    companion object {
        private const val EXTRA_COMMANDS = "commands"

        fun newIntent(context: Context, commands: Array<Command>): Intent {
            return Intent(context, CommandDetailsActivity::class.java)
                    .putExtra(EXTRA_COMMANDS, commands)
        }
    }

}