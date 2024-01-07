package com.braincorp.githandbook.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.LiveData
import com.braincorp.githandbook.R
import com.braincorp.githandbook.adapter.CommandAdapter
import com.braincorp.githandbook.adapter.OnItemClickListener
import com.braincorp.githandbook.adapter.QueryListener
import com.braincorp.githandbook.core.ads.AdLoader
import com.braincorp.githandbook.core.consent.UserConsentManager
import com.braincorp.githandbook.core.dialogue.DialogueHelper
import com.braincorp.githandbook.core.util.getAppVersion
import com.braincorp.githandbook.databinding.ActivityMainBinding
import com.braincorp.githandbook.model.Command
import com.braincorp.githandbook.viewmodel.CommandViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), CoroutineScope, OnItemClickListener {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding!!

    @Inject
    lateinit var userConsentManager: UserConsentManager

    @Inject
    lateinit var adLoader: AdLoader

    @Inject
    lateinit var dialogueHelper: DialogueHelper

    private val job = Job()

    override val coroutineContext: CoroutineContext = job + Dispatchers.Main

    private val viewModel by viewModels<CommandViewModel>()
    private val adapter = CommandAdapter(onItemClickListener = this)

    private var commands: List<Command> = emptyList()
    private var searchView: SearchView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpUi()
        fetchData()
        userConsentManager.getConsentIfRequired(activity = this) {
            adLoader.loadBannerAds(binding.adView)
        }
    }

    override fun onDestroy() {
        job.cancel()
        super.onDestroy()
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

    override fun onItemClick(command: Command) {
        val commands = this.commands.filter { it.name == command.name }.toTypedArray()
        val intent = CommandDetailsActivity.newIntent(this, commands)
        startActivity(intent)
    }

    private fun setUpUi() {
        setSupportActionBar(binding.toolbar)
        binding.recyclerView.adapter = adapter
    }

    private fun fetchData() {
        launch {
            val commands = withContext(Dispatchers.IO) {
                viewModel.getCommandsAsync()
            }.await()
            observeData(commands)
        }
    }

    private fun observeData(data: LiveData<List<Command>>) {
        data.observe(this) { allCommands ->
            commands = allCommands
            val distinctCommands = commands.distinctBy { command ->
                command.name
            }
            adapter.submitLists(allCommands, distinctCommands)
            searchView?.setOnQueryTextListener(QueryListener(adapter, distinctCommands))
        }
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
