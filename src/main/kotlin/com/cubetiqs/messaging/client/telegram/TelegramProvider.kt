package com.cubetiqs.messaging.client.telegram

import com.cubetiqs.messaging.client.provider.MessageProvider
import com.sun.org.slf4j.internal.LoggerFactory
import kotlin.IllegalArgumentException

/**
 * Telegram Provider
 *
 * @author sombochea
 * @since 1.0
 */
class TelegramProvider : MessageProvider {
    private val log = LoggerFactory.getLogger(this::class.java)
    private var _token: String = ""
    private var _chatId: String = ""
    private var _message: TelegramMessage? = null

    fun sendToChatId(chatId: String) = apply {
        this._chatId = chatId
    }

    fun messageToSend(message: TelegramMessage?) = apply {
        this._message = message
    }

    fun setBotToken(token: String) = apply {
        this._token = token
    }

    fun send(
        chatId: String,
        message: TelegramMessage,
    ): TelegramResponse? {
        if (_token.isEmpty() || _token.isBlank()) {
            throw IllegalArgumentException("token must be provide for send message!")
        }

        if (_message?.getText().isNullOrEmpty()) return null

        return try {
            val response = TelegramBotUtils.sendMessage(
                chatId = chatId,
                token = this._token,
                text = this._message!!.getText(),
            )

            TelegramResponse(
                response = response,
            )
        } catch (ex: Exception) {
            log.error("[Telegram] Message send error: ${ex.message}!")
            null
        }
    }

    override fun send(): Any? {
        if (this._message?.getText().isNullOrEmpty()) {
            throw IllegalArgumentException("message must be non-null or non-empty!")
        }

        return send(this._chatId, this._message!!)
    }

    override fun toString(): String {
        return "TelegramProvider(_token='$_token', _chatId='$_chatId', _message=$_message)"
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
                .messageToSend(TelegramChatMessage(text))
                .send()
        }
    }
}