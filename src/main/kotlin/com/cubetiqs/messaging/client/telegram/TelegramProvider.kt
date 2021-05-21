package com.cubetiqs.messaging.client.telegram

import com.cubetiqs.messaging.client.provider.MessageProvider
import com.cubetiqs.messaging.client.util.Loggable
import java.io.File
import java.nio.file.Files
import java.util.*
import kotlin.IllegalArgumentException

/**
 * Telegram Provider
 *
 * @author sombochea
 * @since 1.0
 */
class TelegramProvider : MessageProvider, Loggable {
    private var _token: String = ""
    private var _chatId: String = ""
    private var _message: TelegramMessage? = null
    private var _file: File? = null
    private var _filename: String? = null

    fun sendToChatId(chatId: String) = apply {
        this._chatId = chatId
    }

    fun setMessage(message: String) = apply {
        this._message = TelegramMessage { message }
    }

    fun setMessageToSend(message: TelegramMessage?) = apply {
        this._message = message
    }

    fun setBotToken(token: String) = apply {
        this._token = token
    }

    fun setFile(file: File?) = apply {
        this._file = file
    }

    fun setFilename(filename: String?) = apply {
        this._filename = filename
    }

    private fun send(
        chatId: String,
        message: TelegramMessage,
    ): TelegramResponse? {
        if (_token.isEmpty() || _token.isBlank()) {
            throw IllegalArgumentException("token must be provide for send message!")
        }

        if (this._message == null) {
            this._message = message
        }

        if (_file == null) {
            if (_message?.getText().isNullOrEmpty()) return null
        }

        return try {
            val response = if (_file != null) {
                TelegramBotUtils.sendDocument(
                    chatId = chatId,
                    token = this._token,
                    text = this._message?.getText() ?: "",
                    filename = _filename ?: Date().time.toString(),
                    document = Files.readAllBytes(_file!!.toPath()),
                )
            } else {
                TelegramBotUtils.sendMessage(
                    chatId = chatId,
                    token = this._token,
                    text = this._message!!.getText(),
                )
            }

            TelegramResponse(
                response = response,
            )
        } catch (ex: Exception) {
            log.error("[Telegram] Message send error: ${ex.message}!")
            null
        }
    }

    override fun send(): Any? {
        if (this._file == null) {
            if (this._message?.getText().isNullOrEmpty()) {
                throw IllegalArgumentException("message must be non-null or non-empty!")
            }
        } else {
            if (this._message == null) {
                this._message = TelegramMessage { "" }
            }
        }

        return send(this._chatId, this._message!!)
    }

    override fun toString(): String {
        return "TelegramProvider(_token='$_token', _chatId='$_chatId', _message=$_message, _filename=$_filename, _file=$_file)"
    }

    companion object {
        @JvmStatic
        fun init(token: String): TelegramProvider {
            return TelegramProvider()
                .setBotToken(token)
        }

        @JvmStatic
        fun sendMessage(token: String, chatId: String, text: String): Any? {
            return init(token)
                .sendToChatId(chatId)
                .setMessageToSend(TelegramChatMessage(text))
                .send()
        }
    }
}