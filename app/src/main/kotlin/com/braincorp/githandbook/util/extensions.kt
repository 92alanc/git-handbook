package com.braincorp.githandbook.util

import android.content.Context
import com.braincorp.githandbook.R
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView

fun AdView.loadAnnoyingAds() {
    val adRequest = AdRequest.Builder().build()
    loadAd(adRequest)
}

fun Context.getString(resourceName: String?): String? {
    return try {
        val resId = resources.getIdentifier(resourceName, "string", packageName)
        getString(resId)
    } catch (ex: Exception) {
        null
    }
}

fun Context.getAppName(): String = getString(R.string.app_name)

fun Context.getAppVersion(): String = packageManager.getPackageInfo(packageName, 0).versionName