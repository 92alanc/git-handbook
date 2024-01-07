package com.braincorp.githandbook.commands.ui.adapter

import com.braincorp.githandbook.commands.ui.model.UiCommand

interface OnItemClickListener {
    fun onItemClick(command: UiCommand)
}