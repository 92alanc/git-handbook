package com.braincorp.githandbook.util

import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView

fun AdView.loadAnnoyingAds() {
    val adRequest = AdRequest.Builder().build()
    loadAd(adRequest)
}