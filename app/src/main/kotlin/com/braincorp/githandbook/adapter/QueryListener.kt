package com.braincorp.githandbook.adapter

import androidx.appcompat.widget.SearchView
import com.braincorp.githandbook.model.Command

class QueryListener(
    private val adapter: CommandAdapter,
    private val media: List<Command>
) : SearchView.OnQueryTextListener {

    override fun onQueryTextSubmit(query: String?) = false

    override fun onQueryTextChange(newText: String?): Boolean {
        adapter.filter(media, newText)
        return false
    }

}