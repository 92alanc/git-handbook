package com.braincorp.githandbook.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.braincorp.githandbook.R
import com.braincorp.githandbook.model.Command

class CommandAdapter : ListAdapter<Command, CommandViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommandViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_command, parent, false)
        return CommandViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CommandViewHolder, position: Int) {
        val command = getItem(position)
        val paramsCount = currentList.count { it.name == command.name }
        holder.bindTo(command, paramsCount)
    }

    private companion object DiffCallback : DiffUtil.ItemCallback<Command>() {
        override fun areItemsTheSame(oldItem: Command, newItem: Command): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Command, newItem: Command): Boolean {
            return oldItem.name == newItem.name
                    && oldItem.parameter == newItem.parameter
                    && oldItem.description == newItem.description
                    && oldItem.example == newItem.example
        }
    }

}