package com.braincorp.githandbook.core.dialogue

import androidx.annotation.LayoutRes
import androidx.annotation.StringRes

interface DialogueHelper {

    fun showDialogue(
        @StringRes titleRes: Int,
        @StringRes messageRes: Int,
        onPositiveButtonClicked: () -> Unit
    )

    fun showDialogue(title: String, @StringRes messageRes: Int)

    fun showDialogue(@LayoutRes layoutRes: Int)
}
