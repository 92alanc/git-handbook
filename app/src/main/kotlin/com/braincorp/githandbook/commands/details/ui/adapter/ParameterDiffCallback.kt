package com.braincorp.githandbook.commands.details.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.braincorp.githandbook.commands.list.ui.model.UiParameter

object ParameterDiffCallback : DiffUtil.ItemCallback<UiParameter>() {

    override fun areItemsTheSame(oldItem: UiParameter, newItem: UiParameter): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: UiParameter, newItem: UiParameter): Boolean {
        return oldItem == newItem
    }
}
