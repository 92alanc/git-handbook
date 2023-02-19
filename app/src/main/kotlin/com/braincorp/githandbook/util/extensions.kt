package com.braincorp.githandbook.util

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
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

fun Context.getAppVersion(): String {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        packageManager.getPackageInfo(
            packageName,
            PackageManager.PackageInfoFlags.of(0)
        ).versionName
    } else {
        @Suppress("DEPRECATION")
        packageManager.getPackageInfo(packageName, 0).versionName
    }
}