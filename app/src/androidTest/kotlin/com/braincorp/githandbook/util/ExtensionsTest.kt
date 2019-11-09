package com.braincorp.githandbook.util

import androidx.test.platform.app.InstrumentationRegistry
import com.braincorp.githandbook.R
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ExtensionsTest {

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun shouldGetStringByResourceName() {
        val expected = context.getString(R.string.param_1)

        assertThat(context.getString("param_1")).isEqualTo(expected)
    }

}