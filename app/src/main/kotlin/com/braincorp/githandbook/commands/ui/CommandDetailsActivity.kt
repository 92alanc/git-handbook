package com.braincorp.githandbook.commands.ui

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.braincorp.githandbook.commands.ui.adapter.ParameterAdapter
import com.braincorp.githandbook.commands.ui.model.UiCommand
import com.braincorp.githandbook.commands.ui.model.UiParameter
import com.braincorp.githandbook.core.ads.AdLoader
import com.braincorp.githandbook.databinding.ActivityCommandDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CommandDetailsActivity : AppCompatActivity() {

    private var _binding: ActivityCommandDetailsBinding? = null
    private val binding: ActivityCommandDetailsBinding
        get() = _binding!!

    @Inject
    lateinit var adLoader: AdLoader

    private val adapter by lazy { ParameterAdapter(::configureTexts) }

    private val command by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(EXTRA_COMMAND, UiCommand::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_COMMAND)
        } ?: error("Command not provided")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityCommandDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configureToolbar()
        configureRecyclerView()
        configureTexts(command.parameters.first())
        adLoader.loadBannerAds(binding.adView)
    }

    private fun configureToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = command.name
    }

    private fun configureRecyclerView() {
        binding.recyclerView.adapter = adapter
        adapter.submitList(command)
    }

    private fun configureTexts(parameter: UiParameter) = with(binding) {
        txtDescription.text = parameter.description
        txtExample.text = parameter.example
    }

    companion object {
        private const val EXTRA_COMMAND = "command"

        fun getIntent(context: Context, command: UiCommand): Intent {
            return Intent(context, CommandDetailsActivity::class.java)
                .putExtra(EXTRA_COMMAND, command)
        }
    }

}