package com.cubetiqs.messaging.client.telegram

import com.cubetiqs.messaging.client.util.Loggable
import com.cubetiqs.messaging.client.webclient.WebClientUtils
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.asRequestBody
import java.nio.file.Files

object TelegramBotUtils : Loggable {
    private fun getBotUrl(endpoint: String, token: String = ""): String {
        return "${TelegramConfig.TELEGRAM_API}/bot${token.removePrefix("bot")}/${endpoint.removePrefix("/")}"
    }

    private fun makeRequest(
        request: Request,
    ): Response? {
        log.info("Start send message via telegram bot...")
        return try {
            WebClientUtils.makeRequest(request)
        } catch (ex: Exception) {
            ex.printStackTrace()
            log.error("Telegram make request error {}", ex.message)
            throw TelegramSendException(ex)
        }
    }

    @JvmStatic
    fun sendMessage(
        // usually chat id from chat group
        chatId: String,
        // message to send
        text: String,
        // config prefix for custom token
        token: String = "",
    ): Any? {
        validateTextAndChatId(text, chatId)

        val requestBody = MultipartBody.Builder()
            .addFormDataPart("text", text)
            .addFormDataPart("chat_id", chatId)
            .setType(MultipartBody.FORM)
            .build()

        val url = getBotUrl(TelegramConfig.SEND_MESSAGE, token)
        val request = Request.Builder()
            .url(url)
            .post(requestBody)
            .build()

        val result = makeRequest(request)
        log.info("Telegram complete sent message to {}", chatId)
        return result
    }

    private fun validateTextAndChatId(text: String, chatId: String) {
        if (text.isEmpty() || text.isBlank()) throw IllegalArgumentException("Message is required to send to receiver!")
        if (chatId.isBlank() || chatId.isEmpty()) throw IllegalArgumentException("Chat ID is required to receive the message!")
    }

    @JvmStatic
    fun sendDocument(
        // usually chat id from chat group
        chatId: String,
        // message to send
        text: String,
        // document extension (example: my_file.pdf, my_file.docx, etc)
        filename: String,
        // document in byte array
        document: ByteArray,
        // config prefix for custom token
        token: String = "",
    ): Any? {
        if (document.isEmpty()) throw IllegalArgumentException("Document is required to attach in message!")
        if (chatId.isBlank() || chatId.isEmpty()) throw IllegalArgumentException("Chat ID is required to receive the message!")

        val ext = filename.split(".").lastOrNull() ?: "dat"
        val tempFile = Files.createTempFile(filename.removeSuffix(ext), ".$ext")
        Files.write(tempFile, document)

        val requestBody = MultipartBody.Builder()
            .addFormDataPart(
                "document", filename,
                tempFile.toFile().asRequestBody("application/octet-stream".toMediaTypeOrNull())
            )
            .addFormDataPart("caption", text)
            .addFormDataPart("chat_id", chatId)
            .setType(MultipartBody.FORM)
            .build()

        val url = getBotUrl(TelegramConfig.SEND_DOCUMENT, token)
        val request = Request.Builder()
            .url(url)
            .post(requestBody)
            .build()

        val result = makeRequest(request)
        log.info("Telegram complete sent message to {}", chatId)
        return result
    }
}