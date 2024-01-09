package com.braincorp.githandbook.core.web

import android.content.Context
import android.content.Intent
import android.net.Uri
import javax.inject.Inject

class WebPageViewerImpl @Inject constructor() : WebPageViewer {

    override fun viewWebPage(context: Context, url: String) {
        val intent = Intent(Intent.ACTION_VIEW).setData(Uri.parse(url))
        context.startActivity(intent)
    }
}