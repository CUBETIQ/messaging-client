package com.cubetiqs.messaging.client.telegram

import java.io.Serializable

/**
 * Telegram Message
 *
 * @author sombochea
 * @since 1.0
 */
interface TelegramMessage : Serializable {
    fun getText(): String
}