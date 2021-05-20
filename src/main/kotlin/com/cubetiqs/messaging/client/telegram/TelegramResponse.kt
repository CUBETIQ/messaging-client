package com.cubetiqs.messaging.client.telegram

import java.io.Serializable

/**
 * Telegram Response
 *
 * @author sombochea
 * @since 1.0
 */
class TelegramResponse(
    val request: Any? = null,
    val response: Any? = null,
) : Serializable {
    override fun toString(): String {
        return "TelegramResponse(request=$request, response=$response)"
    }
}