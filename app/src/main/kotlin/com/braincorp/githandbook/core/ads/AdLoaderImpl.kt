package com.braincorp.githandbook.core.ads

import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import javax.inject.Inject

class AdLoaderImpl @Inject constructor() : AdLoader {

    override fun loadBannerAds(target: AdView) {
        val adRequest = AdRequest.Builder().build()
        target.loadAd(adRequest)
    }
}
