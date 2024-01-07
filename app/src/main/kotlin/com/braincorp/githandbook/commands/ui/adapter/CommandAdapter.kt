package com.braincorp.githandbook.commands.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.braincorp.githandbook.commands.ui.model.UiCommand
import com.braincorp.githandbook.databinding.ItemCommandBinding

class CommandAdapter(
    private val onItemClicked: (UiCommand) -> Unit
) : RecyclerView.Adapter<CommandViewHolder>() {

    private var allCommands: List<UiCommand> = emptyList()
    private var data: List<UiCommand> = emptyList()

    fun submitLists(allCommands: List<UiCommand>, distinctCommands: List<UiCommand>) {
        this.allCommands = allCommands
        data = distinctCommands
        notifyDataSetChanged()
    }

    fun filter(commands: List<UiCommand>, searchTerm: String?) {
        searchTerm?.let { query ->
            data = commands.filter { it.name.contains(query, ignoreCase = true) }
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommandViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCommandBinding.inflate(inflater, parent, false)
        return CommandViewHolder(binding, onItemClicked)
    }

    override fun onBindViewHolder(holder: CommandViewHolder, position: Int) {
        val command = data[position]
        val paramsCount = allCommands.count { it.name == command.name }
        holder.bindTo(command, paramsCount)
    }

    override fun getItemCount() = data.size
}
