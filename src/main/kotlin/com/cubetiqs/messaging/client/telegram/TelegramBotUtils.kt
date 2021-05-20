package com.cubetiqs.messaging.client.telegram

import com.cubetiqs.messaging.client.webclient.WebClientUtils
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.asRequestBody
import java.nio.file.Files

object TelegramBotUtils {
    private fun getBotUrl(endpoint: String, token: String = ""): String {
        return "${TelegramConfig.TELEGRAM_API}/bot${token.removePrefix("bot")}/${endpoint.removePrefix("/")}"
    }

    private fun makeRequest(
        request: Request,
    ): Response? {
        println(javaClass.canonicalName + " => Start send message via telegram bot...")
        return try {
            WebClientUtils.makeRequest(request)
        } catch (ex: Exception) {
            ex.printStackTrace()
            println("Make request error @${ex.message}")
            null
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
        println(javaClass.canonicalName + " => Complete sent message to $chatId...")
        return result
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
        println(javaClass.canonicalName + " => Complete sent document to $chatId...")
        return result
    }
}