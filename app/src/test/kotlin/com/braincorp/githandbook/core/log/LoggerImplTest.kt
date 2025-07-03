package com.braincorp.githandbook.core.log

import android.util.Log
import io.mockk.mockkStatic
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import java.io.IOException

private const val TAG = "LOG_APP"

class LoggerImplTest {

    private val logger = LoggerImpl()

    @Before
    fun setUp() {
        mockkStatic(Log::class)
    }

    @Test
    fun `debug should log message`() {
        // GIVEN
        val message = "message"

        // WHEN
        logger.debug(message)

        // THEN
        verify { Log.d(TAG, message) }
    }

    @Test
    fun `error should log exception`() {
        // GIVEN
        val message = "message"
        val exception = IOException()

        // WHEN
        logger.error(message, exception)

        // THEN
        verify { Log.e(TAG, message, exception) }
    }
}
