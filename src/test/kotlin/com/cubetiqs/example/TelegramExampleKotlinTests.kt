package com.cubetiqs.example

import com.cubetiqs.messaging.client.telegram.TelegramBotUtils
import com.cubetiqs.messaging.client.telegram.TelegramConfig
import com.cubetiqs.messaging.client.telegram.TelegramProvider
import org.junit.jupiter.api.Test
import java.io.File

class TelegramExampleKotlinTests {
    private val token = TelegramConfig.POS_SYSTEM_ID
    private val chatId = "-360594386"

    @Test
    fun sendMessage() {
        val text = "Hello World"
        TelegramBotUtils.sendMessage(
            chatId = chatId,
            token = token,
            text = text,
        )
    }

    @Test
    fun sendDocument() {
        val text = "My document caption"
        TelegramBotUtils.sendDocument(
            chatId = chatId,
            token = token,
            text = text,
            filename = "my exam paper.png",
            document = File("src/main/resources/cubetiq.png").readBytes(),
        )
    }

    @Test
    fun sendMessageProvider() {
        val text = "Hello World from Provider"
        TelegramProvider.sendMessage(
            chatId = chatId,
            token = token,
            text = text,
        )
    }
}