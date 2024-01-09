package com.braincorp.githandbook.core.dialogue

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.braincorp.githandbook.R
import javax.inject.Inject

class DialogueHelperImpl @Inject constructor() : DialogueHelper {

    override fun showDialogue(
        context: Context,
        titleRes: Int,
        messageRes: Int,
        onPositiveButtonClicked: () -> Unit
    ) {
        AlertDialog.Builder(context)
            .setTitle(titleRes)
            .setMessage(messageRes)
            .setPositiveButton(R.string.ok) { _, _ ->
                onPositiveButtonClicked()
            }.setNegativeButton(R.string.cancel, null)
            .show()
    }

    override fun showDialogue(context: Context, title: String, messageRes: Int) {
        AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(messageRes)
            .setNeutralButton(R.string.ok, null)
            .setIcon(R.mipmap.ic_launcher)
            .show()
    }

    override fun showDialogue(context: Context, layoutRes: Int) {
        AlertDialog.Builder(context)
            .setView(layoutRes)
            .setNeutralButton(R.string.ok, null)
            .show()
    }
}
