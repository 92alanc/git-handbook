package com.braincorp.githandbook.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.braincorp.githandbook.R
import com.braincorp.githandbook.model.CommandHeader

class CommandAdapter : ListAdapter<CommandHeader, CommandViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommandViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_command, parent, false)
        return CommandViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CommandViewHolder, position: Int) {
        val command = getItem(position)
        holder.bindTo(command)
    }

    private companion object DiffCallback : DiffUtil.ItemCallback<CommandHeader>() {
        override fun areItemsTheSame(oldItem: CommandHeader, newItem: CommandHeader): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CommandHeader, newItem: CommandHeader): Boolean {
            return oldItem.name == newItem.name && oldItem.paramsCount == newItem.paramsCount
        }
    }

}