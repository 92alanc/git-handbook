package com.braincorp.githandbook.core.dialogue

import android.content.Context
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes

interface DialogueHelper {

    fun showDialogue(
        context: Context,
        @StringRes titleRes: Int,
        @StringRes messageRes: Int,
        onPositiveButtonClicked: () -> Unit
    )

    fun showDialogue(context: Context, title: String, @StringRes messageRes: Int)

    fun showDialogue(context: Context, @LayoutRes layoutRes: Int)
}
