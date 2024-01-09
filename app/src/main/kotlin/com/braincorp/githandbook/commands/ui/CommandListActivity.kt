package com.braincorp.githandbook.commands.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.braincorp.githandbook.R
import com.braincorp.githandbook.commands.ui.adapter.CommandAdapter
import com.braincorp.githandbook.commands.ui.adapter.QueryListener
import com.braincorp.githandbook.commands.ui.model.UiCommand
import com.braincorp.githandbook.commands.ui.viewmodel.CommandListUiAction
import com.braincorp.githandbook.commands.ui.viewmodel.CommandListUiState
import com.braincorp.githandbook.commands.ui.viewmodel.CommandViewModel
import com.braincorp.githandbook.core.ads.AdLoader
import com.braincorp.githandbook.core.consent.UserConsentManager
import com.braincorp.githandbook.core.dialogue.DialogueHelper
import com.braincorp.githandbook.core.util.getAppVersion
import com.braincorp.githandbook.core.util.observeFlow
import com.braincorp.githandbook.databinding.ActivityCommandListBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CommandListActivity : AppCompatActivity() {

    private var _binding: ActivityCommandListBinding? = null
    private val binding: ActivityCommandListBinding
        get() = _binding!!

    @Inject
    lateinit var userConsentManager: UserConsentManager

    @Inject
    lateinit var adLoader: AdLoader

    @Inject
    lateinit var dialogueHelper: DialogueHelper

    private val viewModel by viewModels<CommandViewModel>()
    private val adapter by lazy { CommandAdapter(viewModel::onCommandClicked) }

    private var searchView: SearchView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityCommandListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observeViewModelFlows()
        setUpUi()
        userConsentManager.getConsentIfRequired(activity = this) {
            adLoader.loadBannerAds(binding.adView)
        }
        viewModel.getAllCommands()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        menu?.run {
            findItem(R.id.item_search)?.actionView?.let { actionView ->
                searchView = actionView as SearchView
            }

            findItem(R.id.item_privacy_settings)?.run {
                isVisible = userConsentManager.isPrivacyOptionsRequired()
            }
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item_reference -> showReference()
            R.id.item_privacy -> showPrivacyPolicy()
            R.id.item_about -> showAppInfo()
            R.id.item_privacy_settings -> {
                userConsentManager.showPrivacyOptions(activity = this)
                true
            }
            else -> false
        }
    }

    private fun setUpUi() {
        setSupportActionBar(binding.toolbar)
        binding.recyclerView.adapter = adapter
    }

    private fun observeViewModelFlows() {
        observeFlow(viewModel.state, ::onStateChanged)
        observeFlow(viewModel.action, ::onAction)
    }

    private fun onStateChanged(state: CommandListUiState) {
        state.commands?.let {
            adapter.submitList(it)
            searchView?.setOnQueryTextListener(QueryListener(adapter, it))
        }
    }

    private fun onAction(action: CommandListUiAction) = when (action) {
        is CommandListUiAction.ShowCommandDetails -> showCommandDetails(action.command)
    }

    private fun showCommandDetails(command: UiCommand) {
        val intent = CommandDetailsActivity.getIntent(context = this, command)
        startActivity(intent)
    }

    private fun showReference(): Boolean {
        dialogueHelper.showDialogue(
            titleRes = R.string.reference,
            messageRes = R.string.reference_message,
            onPositiveButtonClicked = ::openGitReferencePage
        )
        return true
    }

    private fun showPrivacyPolicy(): Boolean {
        dialogueHelper.showDialogue(R.layout.dialogue_privacy_terms)
        return true
    }

    private fun showAppInfo(): Boolean {
        val appName = getString(R.string.app_name)
        val appVersion = getAppVersion()
        val title = getString(R.string.app_info, appName, appVersion)
        dialogueHelper.showDialogue(title, R.string.developer_info)
        return true
    }

    private fun openGitReferencePage() {
        val url = getString(R.string.git_url)
        val intent = Intent(Intent.ACTION_VIEW).setData(Uri.parse(url))
        startActivity(intent)
    }
}
