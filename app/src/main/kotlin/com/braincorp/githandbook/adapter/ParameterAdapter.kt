package com.braincorp.githandbook.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.braincorp.githandbook.R
import com.braincorp.githandbook.model.Command

class ParameterAdapter(
        private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<ParameterViewHolder>() {

    private var commands: List<Command> = emptyList()

    fun submitList(commands: List<Command>) {
        this.commands = commands
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParameterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_parameter, parent, false)
        return ParameterViewHolder(itemView, onItemClickListener)
    }

    override fun onBindViewHolder(holder: ParameterViewHolder, position: Int) {
        val command = commands[position]
        holder.bindTo(command)
    }

    override fun getItemCount() = commands.size

}