package com.braincorp.githandbook.commands.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.braincorp.githandbook.commands.ui.model.UiCommand
import com.braincorp.githandbook.databinding.ItemCommandBinding

class CommandAdapter(
    private val onItemClicked: (UiCommand) -> Unit
) : ListAdapter<UiCommand, CommandViewHolder>(CommandDiffCallback) {

    fun filter(commands: List<UiCommand>, searchTerm: String?) {
        searchTerm?.let { query ->
            val filteredList = commands.filter { it.name.contains(query, ignoreCase = true) }
            submitList(filteredList)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommandViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCommandBinding.inflate(inflater, parent, false)
        return CommandViewHolder(binding, onItemClicked)
    }

    override fun onBindViewHolder(holder: CommandViewHolder, position: Int) {
        val command = getItem(position)
        val paramsCount = command.parameters.size
        holder.bindTo(command, paramsCount)
    }
}
