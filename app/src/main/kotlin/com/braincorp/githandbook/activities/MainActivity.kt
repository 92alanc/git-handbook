package com.braincorp.githandbook.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.braincorp.githandbook.R
import com.braincorp.githandbook.adapter.CommandAdapter
import com.braincorp.githandbook.adapter.OnItemClickListener
import com.braincorp.githandbook.adapter.QueryListener
import com.braincorp.githandbook.model.Command
import com.braincorp.githandbook.util.getAppName
import com.braincorp.githandbook.util.getAppVersion
import com.braincorp.githandbook.util.loadAnnoyingAds
import com.braincorp.githandbook.viewmodel.CommandViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(R.layout.activity_main),
        CoroutineScope,
        OnItemClickListener {

    private val job = Job()

    override val coroutineContext: CoroutineContext = job + Dispatchers.Main

    private val viewModel by viewModel<CommandViewModel>()
    private val adapter = CommandAdapter(onItemClickListener = this)

    private var commands: List<Command> = emptyList()
    private var searchView: SearchView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        ad_view.loadAnnoyingAds()
        recycler_view.adapter = adapter
        fetchData()
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
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.item_reference -> showReference()
            R.id.item_privacy -> showPrivacyPolicy()
            R.id.item_about -> showAppInfo()
            else -> false
        }
    }

    override fun onItemClick(command: Command) {
        val commands = this.commands.filter { it.name == command.name }.toTypedArray()
        val intent = CommandDetailsActivity.newIntent(this, commands)
        startActivity(intent)
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
        data.observe(this, Observer { allCommands ->
            commands = allCommands
            val distinctCommands = commands.distinctBy {
                command -> command.name
            }
            adapter.submitLists(allCommands, distinctCommands)
            searchView?.setOnQueryTextListener(QueryListener(adapter, distinctCommands))
        })
    }

    private fun showReference(): Boolean {
        AlertDialog.Builder(this)
                .setTitle(R.string.reference)
                .setMessage(R.string.reference_message)
                .setPositiveButton(R.string.ok) { _, _ ->
                    openGitReferencePage()
                }.setNegativeButton(R.string.cancel, null)
                .show()
        return true
    }

    private fun showPrivacyPolicy(): Boolean {
        AlertDialog.Builder(this)
                .setView(R.layout.dialogue_privacy_terms)
                .setNeutralButton(R.string.ok, null)
                .show()
        return true
    }

    private fun showAppInfo(): Boolean {
        val title = getString(R.string.app_info, getAppName(), getAppVersion())
        AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(R.string.developer_info)
                .setNeutralButton(R.string.ok, null)
                .setIcon(R.mipmap.ic_launcher)
                .show()
        return true
    }

    private fun openGitReferencePage() {
        val url = getString(R.string.git_url)
        val intent = Intent(Intent.ACTION_VIEW).setData(Uri.parse(url))
        startActivity(intent)
    }

}