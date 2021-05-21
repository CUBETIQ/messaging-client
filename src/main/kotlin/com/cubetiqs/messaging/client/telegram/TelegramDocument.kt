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
    private val parseMode: TelegramParseMode? = null,
) : TelegramMessage {
    override fun getText(): String {
        return text ?: ""
    }

    override fun getParseMode(): TelegramParseMode? {
        return parseMode ?: TelegramParseMode.MARKDOWN
    }
}