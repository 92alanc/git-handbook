package com.braincorp.githandbook.commands.list.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.braincorp.githandbook.R
import com.braincorp.githandbook.commands.details.ui.CommandDetailsActivity
import com.braincorp.githandbook.commands.list.ui.adapter.CommandAdapter
import com.braincorp.githandbook.commands.list.ui.tools.QueryListener
import com.braincorp.githandbook.commands.list.ui.model.UiCommand
import com.braincorp.githandbook.commands.list.ui.viewmodel.CommandListUiAction
import com.braincorp.githandbook.commands.list.ui.viewmodel.CommandListUiState
import com.braincorp.githandbook.commands.list.ui.viewmodel.CommandListViewModel
import com.braincorp.githandbook.core.ads.AdLoader
import com.braincorp.githandbook.core.consent.UserConsentManager
import com.braincorp.githandbook.core.dialogue.DialogueHelper
import com.braincorp.githandbook.core.util.getAppVersion
import com.braincorp.githandbook.core.util.observeFlow
import com.braincorp.githandbook.core.web.WebPageViewer
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

    @Inject
    lateinit var webPageViewer: WebPageViewer

    private val viewModel by viewModels<CommandListViewModel>()
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
            R.id.item_reference -> {
                viewModel.onReferenceMenuItemClicked()
                true
            }

            R.id.item_privacy -> {
                viewModel.onPrivacyPolicyMenuItemClicked()
                true
            }

            R.id.item_about -> {
                viewModel.onAppInfoMenuItemClicked()
                true
            }

            R.id.item_privacy_settings -> {
                viewModel.onPrivacySettingsMenuItemClicked()
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

        is CommandListUiAction.ShowReference -> showReference()

        is CommandListUiAction.ShowPrivacyPolicy -> showPrivacyPolicy()

        is CommandListUiAction.ShowAppInfo -> showAppInfo()

        is CommandListUiAction.ShowPrivacySettings -> userConsentManager.showPrivacyOptions(
            activity = this
        )

        is CommandListUiAction.ViewWebPage -> webPageViewer.viewWebPage(
            context = this,
            action.url
        )
    }

    private fun showCommandDetails(command: UiCommand) {
        val intent = CommandDetailsActivity.getIntent(context = this, command)
        startActivity(intent)
    }

    private fun showReference() {
        dialogueHelper.showDialogue(
            context = this,
            titleRes = R.string.reference,
            messageRes = R.string.reference_message,
            onPositiveButtonClicked = viewModel::onReferenceDialogueButtonClicked
        )
    }

    private fun showPrivacyPolicy() {
        dialogueHelper.showDialogue(context = this, R.layout.dialogue_privacy_terms)
    }

    private fun showAppInfo() {
        val appName = getString(R.string.app_name)
        val appVersion = getAppVersion()
        val title = getString(R.string.app_info, appName, appVersion)
        dialogueHelper.showDialogue(context = this, title, R.string.developer_info)
    }
}
