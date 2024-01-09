package com.braincorp.githandbook.core.web

import android.content.Context

interface WebPageViewer {

    fun viewWebPage(context: Context, url: String)
}
