package com.braincorp.githandbook.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.braincorp.githandbook.R
import com.braincorp.githandbook.adapter.CommandAdapter
import com.braincorp.githandbook.adapter.OnItemClickListener
import com.braincorp.githandbook.model.Command
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
        data.observe(this, Observer {
            commands = it
            adapter.submitList(it)
        })
    }

}