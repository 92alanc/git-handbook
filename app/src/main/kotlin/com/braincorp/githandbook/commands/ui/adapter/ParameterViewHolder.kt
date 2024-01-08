package com.braincorp.githandbook.commands.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.braincorp.githandbook.commands.ui.model.UiParameter
import com.braincorp.githandbook.databinding.ItemParameterBinding

class ParameterViewHolder(
    private val binding: ItemParameterBinding,
    private val onItemClicked: (UiParameter) -> Unit
) : RecyclerView.ViewHolder(binding.txtParameter) {

    fun bindTo(parameter: UiParameter) {
        // TODO
        binding.txtParameter.text = if (parameter.name == null) {
            "command name"
        } else {
            "command name ${parameter.name}"
        }

        binding.txtParameter.setOnClickListener {
            onItemClicked(parameter)
        }
    }
}
