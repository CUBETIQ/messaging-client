package com.cubetiqs.messaging.client.telegram

import com.cubetiqs.messaging.client.util.ConfigUtils

object TelegramConfig {
    const val TELEGRAM_API = "https://api.telegram.org"
    const val SEND_DOCUMENT = "/sendDocument"
    const val SEND_MESSAGE = "/sendMessage"
    private const val CUBETIQ_TELEGRAM_TOKEN = "CUBETIQ_TELEGRAM_TOKEN"
    private const val CUBETIQ_TELEGRAM_RECEIVER = "CUBETIQ_TELEGRAM_RECEIVER"

    @JvmStatic
    fun getToken(): String {
        return ConfigUtils.getEnv(CUBETIQ_TELEGRAM_TOKEN, ConfigUtils.getProperty(CUBETIQ_TELEGRAM_TOKEN)) ?: throw NullPointerException("CUBETIQ_TELEGRAM_TOKEN is required for Telegram Sender!")
    }

    @JvmStatic
    fun getReceiver(): String {
        return ConfigUtils.getEnv(CUBETIQ_TELEGRAM_RECEIVER, ConfigUtils.getProperty(CUBETIQ_TELEGRAM_RECEIVER, "-360594386")) ?: throw NullPointerException("CUBETIQ_TELEGRAM_RECEIVER is required for Telegram Receiver!")
    }
}