package com.braincorp.githandbook.core.dialogue

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.braincorp.githandbook.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DialogueHelperImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : DialogueHelper {

    override fun showDialogue(
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

    override fun showDialogue(title: String, messageRes: Int) {
        AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(messageRes)
            .setNeutralButton(R.string.ok, null)
            .setIcon(R.mipmap.ic_launcher)
            .show()
    }

    override fun showDialogue(layoutRes: Int) {
        AlertDialog.Builder(context)
            .setView(layoutRes)
            .setNeutralButton(R.string.ok, null)
            .show()
    }
}
