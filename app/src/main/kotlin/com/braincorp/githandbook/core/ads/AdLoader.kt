package com.braincorp.githandbook.core.ads

import com.google.android.gms.ads.AdView

interface AdLoader {

    fun loadBannerAds(target: AdView)
}
