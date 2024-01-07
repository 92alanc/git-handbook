package com.braincorp.githandbook.commands.ui.adapter

import androidx.appcompat.widget.SearchView
import com.braincorp.githandbook.commands.ui.model.UiCommand

class QueryListener(
    private val adapter: CommandAdapter,
    private val media: List<UiCommand>
) : SearchView.OnQueryTextListener {

    override fun onQueryTextSubmit(query: String?) = false

    override fun onQueryTextChange(newText: String?): Boolean {
        adapter.filter(media, newText)
        return false
    }

}