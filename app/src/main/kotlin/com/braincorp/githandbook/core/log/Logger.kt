package com.braincorp.githandbook.core.log

interface Logger {

    fun debug(message: String)

    fun error(message: String, t: Throwable)
}
