package com.cubetiqs.example

import com.cubetiqs.messaging.client.telegram.TelegramBotUtils
import com.cubetiqs.messaging.client.telegram.TelegramConfig
import com.cubetiqs.messaging.client.telegram.TelegramParseMode
import com.cubetiqs.messaging.client.telegram.TelegramProvider
import org.junit.jupiter.api.Test
import java.io.File

class TelegramExampleKotlinTests {
    private val token = "1825551011:AAEJg4Y9JtmT4aiW7nz0xOxneyPdruTv8WQ" //TelegramConfig.getToken()
    private val chatId = TelegramConfig.getReceiver()

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

    @Test
    fun sendMessageProviderWithFile() {
        val text = "Hello World from Provider with File"
        val provider = TelegramProvider.init(
            token
        )

        provider
            .setFile(File("src/main/resources/cubetiq.png"))
            .setFilename("cubetiq.png")
            .setMessage(text)
            .sendToChatId(chatId)
            .send()
    }

    @Test
    fun sendMessageProviderWithParseModeHtml() {
        val text = "<b>Hello World<b> from Provider with HTML parse mode!"
        TelegramProvider.init(
            token = token,
        ).setMessage(text, TelegramParseMode.HTML)
            .sendToChatId(chatId)
            .send()
    }

    @Test
    fun sendMessageProviderWithParseModeMarkdown() {
        val text = "*Hello World* from Provider with MARKDOWN parse mode!"
        TelegramProvider.init(
            token = token,
        ).setMessage(text, TelegramParseMode.MARKDOWN)
            .sendToChatId(chatId)
            .send()
    }
}