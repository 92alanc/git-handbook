package com.braincorp.githandbook.commands.ui

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.braincorp.githandbook.commands.ui.adapter.OnItemClickListener
import com.braincorp.githandbook.commands.ui.adapter.ParameterAdapter
import com.braincorp.githandbook.core.ads.AdLoader
import com.braincorp.githandbook.databinding.ActivityCommandDetailsBinding
import com.braincorp.githandbook.commands.data.model.Command
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CommandDetailsActivity : AppCompatActivity(), OnItemClickListener {

    private var _binding: ActivityCommandDetailsBinding? = null
    private val binding: ActivityCommandDetailsBinding
        get() = _binding!!

    @Inject
    lateinit var adLoader: AdLoader

    private val adapter = ParameterAdapter(onItemClickListener = this)

    private lateinit var commands: List<Command>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityCommandDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        parseIntent()
        configureToolbar()
        configureRecyclerView()
        configureTexts(commands.first())
        adLoader.loadBannerAds(binding.adView)
    }

    override fun onItemClick(command: Command) {
        configureTexts(command)
    }

    @Suppress("UNCHECKED_CAST")
    private fun parseIntent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableArrayExtra(EXTRA_COMMANDS, Command::class.java)?.let {
                commands = it.toList()
            }
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableArrayExtra(EXTRA_COMMANDS)?.let {
                commands = it.toList() as List<Command>
            }
        }
    }

    private fun configureToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = commands.first().name
    }

    private fun configureRecyclerView() {
        binding.recyclerView.adapter = adapter
        adapter.submitList(commands)
    }

    private fun configureTexts(command: Command) = with(binding) {
        txtDescription.text = command.description
        txtExample.text = command.example
    }

    companion object {
        private const val EXTRA_COMMANDS = "commands"

        fun newIntent(context: Context, commands: Array<Command>): Intent {
            return Intent(context, CommandDetailsActivity::class.java)
                    .putExtra(EXTRA_COMMANDS, commands)
        }
    }

}