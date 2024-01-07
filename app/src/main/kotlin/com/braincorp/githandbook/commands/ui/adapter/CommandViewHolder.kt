package com.braincorp.githandbook.commands.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.braincorp.githandbook.R
import com.braincorp.githandbook.commands.ui.model.UiCommand
import com.braincorp.githandbook.databinding.ItemCommandBinding

class CommandViewHolder(
    private val binding: ItemCommandBinding,
    private val onItemClicked: (UiCommand) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bindTo(item: UiCommand, paramsCount: Int) {
        binding.txtName.text = item.name
        binding.txtParams.text = binding.root.context.resources.getQuantityString(
            R.plurals.params_plural,
            paramsCount,
            paramsCount
        )
        binding.root.setOnClickListener {
            onItemClicked(item)
        }
    }
}
