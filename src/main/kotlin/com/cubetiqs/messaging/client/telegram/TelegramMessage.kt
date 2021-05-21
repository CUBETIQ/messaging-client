package com.cubetiqs.messaging.client.telegram

import java.io.Serializable

/**
 * Telegram Message
 *
 * @author sombochea
 * @since 1.0
 */
@FunctionalInterface
fun interface TelegramMessage : Serializable {
    fun getText(): String
    fun getParseMode(): TelegramParseMode? {
        return null
    }
}