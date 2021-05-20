package com.cubetiqs.messaging.client.telegram

/**
 * Telegram Chat Message
 *
 * @author sombochea
 * @since 1.0
 */
open class TelegramChatMessage(
    private val text: String? = null,
) : TelegramMessage {
    override fun getText(): String {
        return text ?: ""
    }
}