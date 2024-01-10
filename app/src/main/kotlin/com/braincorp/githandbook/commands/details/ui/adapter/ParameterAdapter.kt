package com.braincorp.githandbook.commands.details.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.braincorp.githandbook.commands.list.ui.model.UiCommand
import com.braincorp.githandbook.commands.list.ui.model.UiParameter
import com.braincorp.githandbook.databinding.ItemParameterBinding

class ParameterAdapter(
    private val onItemClicked: (UiParameter) -> Unit
) : ListAdapter<UiParameter, ParameterViewHolder>(ParameterDiffCallback) {

    private var parentCommand: UiCommand? = null

    fun submitList(command: UiCommand) {
        submitList(command.parameters)
        parentCommand = command
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParameterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemParameterBinding.inflate(inflater, parent, false)
        return ParameterViewHolder(binding, onItemClicked)
    }

    override fun onBindViewHolder(holder: ParameterViewHolder, position: Int) {
        parentCommand?.let {
            val parameter = getItem(position)
            holder.bindTo(parameter, it)
        }
    }
}
