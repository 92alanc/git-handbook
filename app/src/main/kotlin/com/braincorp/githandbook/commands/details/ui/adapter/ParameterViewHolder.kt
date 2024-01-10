package com.braincorp.githandbook.commands.details.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.braincorp.githandbook.commands.list.ui.model.UiCommand
import com.braincorp.githandbook.commands.list.ui.model.UiParameter
import com.braincorp.githandbook.databinding.ItemParameterBinding

class ParameterViewHolder(
    private val binding: ItemParameterBinding,
    private val onItemClicked: (UiParameter) -> Unit
) : RecyclerView.ViewHolder(binding.txtParameter) {

    fun bindTo(parameter: UiParameter, parentCommand: UiCommand) {
        binding.txtParameter.text = if (parameter.name == null) {
            parentCommand.name
        } else {
            "${parentCommand.name} ${parameter.name}"
        }

        binding.txtParameter.setOnClickListener {
            onItemClicked(parameter)
        }
    }
}
