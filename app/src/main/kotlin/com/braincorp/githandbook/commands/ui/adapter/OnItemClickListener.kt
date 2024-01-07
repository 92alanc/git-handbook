package com.braincorp.githandbook.commands.ui.adapter

import com.braincorp.githandbook.commands.data.model.Command

interface OnItemClickListener {
    fun onItemClick(command: Command)
}