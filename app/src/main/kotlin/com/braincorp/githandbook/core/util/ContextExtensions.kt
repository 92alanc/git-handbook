package com.braincorp.githandbook.core.util

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build

@SuppressLint("DiscouragedApi")
fun Context.getStringFromResourceName(resourceName: String?): String? {
    return try {
        val resId = resources.getIdentifier(resourceName, "string", packageName)
        getString(resId)
    } catch (ex: Exception) {
        null
    }
}

fun Context.getAppVersion(): String {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        packageManager.getPackageInfo(
            packageName,
            PackageManager.PackageInfoFlags.of(0)
        ).versionName
    } else {
        packageManager.getPackageInfo(packageName, 0).versionName
    }
}
