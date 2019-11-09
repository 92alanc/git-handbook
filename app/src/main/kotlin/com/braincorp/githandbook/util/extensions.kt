package com.braincorp.githandbook.util

import android.content.Context
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView

fun AdView.loadAnnoyingAds() {
    val adRequest = AdRequest.Builder().build()
    loadAd(adRequest)
}

fun Context.getString(resourceName: String): String {
    val resId = resources.getIdentifier(resourceName, "string", packageName)
    return getString(resId)
}