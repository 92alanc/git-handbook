package com.braincorp.githandbook.commands.ui

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import com.braincorp.githandbook.commands.ui.adapter.OnItemClickListener
import com.braincorp.githandbook.commands.ui.adapter.ParameterAdapter
import com.braincorp.githandbook.commands.ui.model.UiCommand
import com.braincorp.githandbook.core.ads.AdLoader
import com.braincorp.githandbook.databinding.ActivityCommandDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.parcelize.Parcelize
import javax.inject.Inject

@AndroidEntryPoint
class CommandDetailsActivity : AppCompatActivity(), OnItemClickListener {

    private var _binding: ActivityCommandDetailsBinding? = null
    private val binding: ActivityCommandDetailsBinding
        get() = _binding!!

    @Inject
    lateinit var adLoader: AdLoader

    private val adapter = ParameterAdapter(onItemClickListener = this)

    private val command by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(EXTRA_COMMAND, UiCommand::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_COMMAND)
        }
    }

    private lateinit var commands: List<UiCommand>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityCommandDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configureToolbar()
        configureRecyclerView()
        configureTexts(commands.first())
        adLoader.loadBannerAds(binding.adView)
    }

    override fun onItemClick(command: UiCommand) {
        configureTexts(command)
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

    private fun configureTexts(command: UiCommand) = with(binding) {
        txtDescription.text = command.description
        txtExample.text = command.example
    }

    companion object {
        private const val EXTRA_COMMAND = "command"

        fun getIntent(context: Context, command: UiCommand): Intent {
            return Intent(context, CommandDetailsActivity::class.java)
                .putExtra(EXTRA_COMMAND, command)
        }
    }

}