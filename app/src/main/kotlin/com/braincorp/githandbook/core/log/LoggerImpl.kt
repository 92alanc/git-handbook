package com.braincorp.githandbook.core.log

import android.util.Log
import javax.inject.Inject

private const val TAG = "LOG_APP"

class LoggerImpl @Inject constructor() : Logger {

    override fun debug(message: String) {
        Log.d(TAG, message)
    }

    override fun error(message: String, t: Throwable) {
        Log.e(TAG, message, t)
    }
}
