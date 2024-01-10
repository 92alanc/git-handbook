package com.braincorp.githandbook.commands.list.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UiCommand(
    val id: Int,
    val name: String,
    val parameters: List<UiParameter>
) : Parcelable
