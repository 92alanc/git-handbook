package com.braincorp.githandbook.adapter

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import com.braincorp.githandbook.R
import com.braincorp.githandbook.model.Command

class ParameterViewHolder(
        itemView: View,
        private val onItemClickListener: OnItemClickListener
) : ViewHolder<Command>(itemView) {

    private val txtParameter by bindView<TextView>(R.id.txt_parameter)

    @SuppressLint("SetTextI18n")
    fun bindTo(command: Command) {
        txtParameter.text = if (command.parameter == null)
            command.name
        else
            "${command.name} ${command.parameter}"

        itemView.setOnClickListener {
            onItemClickListener.onItemClick(command)
        }
    }

}