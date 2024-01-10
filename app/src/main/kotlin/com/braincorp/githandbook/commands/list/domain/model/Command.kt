package com.braincorp.githandbook.commands.list.domain.model

data class Command(
    val id: Int,
    val name: String,
    val parameters: List<Parameter>
)
