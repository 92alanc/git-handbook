package com.braincorp.githandbook.commands.details.ui

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.braincorp.githandbook.commands.details.ui.adapter.ParameterAdapter
import com.braincorp.githandbook.commands.details.ui.viewmodel.CommandDetailsUiState
import com.braincorp.githandbook.commands.details.ui.viewmodel.CommandDetailsViewModel
import com.braincorp.githandbook.commands.list.ui.model.UiCommand
import com.braincorp.githandbook.core.ads.AdLoader
import com.braincorp.githandbook.core.util.observeFlow
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

    private val viewModel by viewModels<CommandDetailsViewModel>()
    private val adapter by lazy { ParameterAdapter(viewModel::onParameterItemClicked) }

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
        setUpUi()
        observeFlow(viewModel.state, ::onStateChanged)
        viewModel.start(command)
    }

    private fun setUpUi() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.recyclerView.adapter = adapter.apply {
            submitList(command)
        }
        adLoader.loadBannerAds(binding.adView)
    }

    private fun onStateChanged(state: CommandDetailsUiState) = with(state) {
        title?.let(::setTitle)
        description?.let(binding.txtDescription::setText)
        example?.let(binding.txtExample::setText)
    }

    companion object {
        private const val EXTRA_COMMAND = "command"

        fun getIntent(context: Context, command: UiCommand): Intent {
            return Intent(context, CommandDetailsActivity::class.java)
                .putExtra(EXTRA_COMMAND, command)
        }
    }
}
