package com.braincorp.githandbook.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.braincorp.githandbook.databinding.ItemParameterBinding
import com.braincorp.githandbook.model.Command

class ParameterViewHolder(
    private val binding: ItemParameterBinding,
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.ViewHolder(binding.txtParameter) {

    @SuppressLint("SetTextI18n")
    fun bindTo(command: Command) {
        binding.txtParameter.text = if (command.parameter == null) {
            command.name
        } else {
            "${command.name} ${command.parameter}"
        }

        binding.txtParameter.setOnClickListener {
            onItemClickListener.onItemClick(command)
        }
    }
}
