package com.braincorp.githandbook.adapter

import com.braincorp.githandbook.model.Command

interface OnItemClickListener {
    fun onItemClick(command: Command)
}