package com.braincorp.githandbook.commands.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.braincorp.githandbook.commands.ui.model.UiCommand

object CommandDiffCallback : DiffUtil.ItemCallback<UiCommand>() {

    override fun areItemsTheSame(oldItem: UiCommand, newItem: UiCommand): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UiCommand, newItem: UiCommand): Boolean {
        return oldItem == newItem
    }
}
