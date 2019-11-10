package com.braincorp.githandbook.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.braincorp.githandbook.R
import com.braincorp.githandbook.adapter.CommandAdapter
import com.braincorp.githandbook.model.Command
import com.braincorp.githandbook.util.loadAnnoyingAds
import com.braincorp.githandbook.viewmodel.CommandViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(R.layout.activity_main),
        CoroutineScope,
        CommandAdapter.OnItemClickListener {

    private val job = Job()

    override val coroutineContext: CoroutineContext = job + Dispatchers.Main

    private lateinit var viewModel: CommandViewModel
    private val adapter = CommandAdapter(onItemClickListener = this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        viewModel = ViewModelProviders.of(this).get(CommandViewModel::class.java)
        ad_view.loadAnnoyingAds()
        recycler_view.adapter = adapter
        fetchData()
    }

    override fun onDestroy() {
        job.cancel()
        super.onDestroy()
    }

    override fun onItemClick(command: Command) {
        val intent = CommandActivity.newIntent(this, command.name)
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
        data.observe(this, Observer(adapter::submitList))
    }

}