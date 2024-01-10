package com.braincorp.githandbook.commands.list.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UiParameter(
    val name: String?,
    val description: String,
    val example: String
) : Parcelable
