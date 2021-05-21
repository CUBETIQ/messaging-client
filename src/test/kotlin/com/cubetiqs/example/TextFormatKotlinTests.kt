package com.cubetiqs.example

import com.cubetiqs.messaging.client.util.textFormat
import org.junit.jupiter.api.Test
import com.cubetiqs.util.TextFormat
import com.cubetiqs.util.LogUtil
import org.junit.jupiter.api.Assertions

class TextFormatKotlinTests {
    @Test
    fun textFormatter() {
        val text = "Hello, I'm {0} {1}"
        val result = TextFormat.withText(text).format("Sambo", "Chea")
        LogUtil.info("Result 1 => {}", result)

        Assertions.assertEquals("Hello, I'm Sambo Chea", result)
    }

    @Test
    fun textFormatterExtension() {
        val text = "Hello, I'm {0} {1}"
        val result = text.textFormat("Sambo", "Chea")
        LogUtil.info("Result 2 => {}", result)

        Assertions.assertEquals("Hello, I'm Sambo Chea", result)
    }
}