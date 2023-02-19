package com.braincorp.githandbook.adapter

import androidx.recyclerview.widget.RecyclerView
import com.braincorp.githandbook.R
import com.braincorp.githandbook.databinding.ItemCommandBinding
import com.braincorp.githandbook.model.Command

class CommandViewHolder(
    private val binding: ItemCommandBinding,
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bindTo(item: Command, paramsCount: Int) {
        binding.txtName.text = item.name
        binding.txtParams.text = binding.root.context.resources.getQuantityString(
            R.plurals.params_plural,
            paramsCount,
            paramsCount
        )
        binding.root.setOnClickListener {
            onItemClickListener.onItemClick(item)
        }
    }
}
