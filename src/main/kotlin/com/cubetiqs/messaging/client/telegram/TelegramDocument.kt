package com.cubetiqs.messaging.client.telegram

/**
 * Telegram Chat Document
 *
 * @author sombochea
 * @since 1.0
 */
open class TelegramDocument(
    val document: TelegramFileDocument? = null,
    private val text: String? = null,
) : TelegramMessage {
    override fun getText(): String {
        return text ?: ""
    }
}