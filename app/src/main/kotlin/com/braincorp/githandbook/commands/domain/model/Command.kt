package com.braincorp.githandbook.commands.domain.model

data class Command(
    val id: Int,
    val name: String,
    val parameters: List<Parameter>
)
