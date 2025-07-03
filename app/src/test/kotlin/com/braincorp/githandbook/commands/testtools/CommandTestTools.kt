package com.braincorp.githandbook.commands.testtools

import com.braincorp.githandbook.commands.list.data.model.DbCommand
import com.braincorp.githandbook.commands.list.data.model.DbDescription
import com.braincorp.githandbook.commands.list.data.model.DbParameter
import com.braincorp.githandbook.commands.list.domain.model.Command
import com.braincorp.githandbook.commands.list.domain.model.Parameter
import com.braincorp.githandbook.commands.list.ui.model.UiCommand
import com.braincorp.githandbook.commands.list.ui.model.UiParameter

fun stubDbCommand() = DbCommand(id = 1, name = "git add")

fun stubDbParameterList() = listOf(
    DbParameter(id = 1, token = null, commandId = 1, descriptionId = 1),
    DbParameter(id = 2, token = 1, commandId = 1, descriptionId = 2)
)

fun stubDbDescriptionList() = listOf(
    DbDescription(id = 1, token = 1),
    DbDescription(id = 2, token = 2)
)

fun stubDbCommandList() = listOf(
    DbCommand(id = 1, name = "git add"),
    DbCommand(id = 2, name = "git log")
)

fun stubDbDescription() = DbDescription(id = 1, token = 1)

fun stubCommandList() = listOf(
    Command(
        id = 1,
        name = "git add",
        parameters = listOf(
            Parameter(
                name = ".",
                description = "Adds everything",
                example = "git add ."
            ),
            Parameter(
                name = "[file_name]",
                description = "Adds a specific file",
                example = "git add MyClass.kt"
            )
        )
    ),
    Command(
        id = 2,
        name = "git log",
        parameters = listOf(
            Parameter(name = null, description = "Shows a log", example = "git log")
        )
    )
)

fun stubUiCommand() = UiCommand(
    id = 1,
    name = "git add",
    parameters = listOf(
        UiParameter(name = null, description = "Adds stuff", example = "git add")
    )
)

fun stubUiParameter() = UiParameter(name = null, description = "Adds stuff", example = "git add")
