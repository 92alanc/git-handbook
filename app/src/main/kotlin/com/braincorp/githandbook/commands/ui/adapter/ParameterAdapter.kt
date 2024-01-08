package com.braincorp.githandbook.commands.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.braincorp.githandbook.commands.ui.model.UiParameter
import com.braincorp.githandbook.databinding.ItemParameterBinding

class ParameterAdapter(
    private val onItemClicked: (UiParameter) -> Unit
) : RecyclerView.Adapter<ParameterViewHolder>() {

    private var parameters: List<UiParameter> = emptyList()

    fun submitList(parameters: List<UiParameter>) {
        this.parameters = parameters
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParameterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemParameterBinding.inflate(inflater, parent, false)
        return ParameterViewHolder(binding, onItemClicked)
    }

    override fun onBindViewHolder(holder: ParameterViewHolder, position: Int) {
        val command = parameters[position]
        holder.bindTo(command)
    }

    override fun getItemCount() = parameters.size
}
