package com.braincorp.githandbook.commands.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.braincorp.githandbook.commands.ui.model.UiCommand
import com.braincorp.githandbook.databinding.ItemParameterBinding

class ParameterAdapter(
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<ParameterViewHolder>() {

    private var commands: List<UiCommand> = emptyList()

    fun submitList(commands: List<UiCommand>) {
        this.commands = commands
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParameterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemParameterBinding.inflate(inflater, parent, false)
        return ParameterViewHolder(binding, onItemClickListener)
    }

    override fun onBindViewHolder(holder: ParameterViewHolder, position: Int) {
        val command = commands[position]
        holder.bindTo(command)
    }

    override fun getItemCount() = commands.size

}