package com.braincorp.githandbook.commands.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.braincorp.githandbook.databinding.ItemCommandBinding
import com.braincorp.githandbook.commands.data.model.Command

class CommandAdapter(
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<CommandViewHolder>() {

    private var allCommands: List<Command> = emptyList()
    private var data: List<Command> = emptyList()

    fun submitLists(allCommands: List<Command>, distinctCommands: List<Command>) {
        this.allCommands = allCommands
        data = distinctCommands
        notifyDataSetChanged()
    }

    fun filter(commands: List<Command>, searchTerm: String?) {
        searchTerm?.let { query ->
            data = commands.filter { it.name.contains(query, ignoreCase = true) }
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommandViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCommandBinding.inflate(inflater, parent, false)
        return CommandViewHolder(binding, onItemClickListener)
    }

    override fun onBindViewHolder(holder: CommandViewHolder, position: Int) {
        val command = data[position]
        val paramsCount = allCommands.count { it.name == command.name }
        holder.bindTo(command, paramsCount)
    }

    override fun getItemCount() = data.size

}