package com.cubetiqs.messaging.client.sms.twlio

import com.cubetiqs.messaging.client.util.ConfigUtils

object TwilioConfig {
    const val ENDPOINT = "https://api.twilio.com/2010-04-01/Accounts"
    private const val CUBETIQ_TWILIO_TOKEN = "CUBETIQ_TWILIO_TOKEN"
    private const val CUBETIQ_TWILIO_ID = "CUBETIQ_TWILIO_ID"
    private const val CUBETIQ_TWILIO_SENDER = "CUBETIQ_TWILIO_SENDER"

    @JvmStatic
    fun getAccountToken(): String {
        return ConfigUtils.getEnv(CUBETIQ_TWILIO_TOKEN, ConfigUtils.getProperty(CUBETIQ_TWILIO_TOKEN)) ?: throw NullPointerException("CUBETIQ_TWILIO_TOKEN is required!")
    }

    @JvmStatic
    fun getAccountId(): String {
        return ConfigUtils.getEnv(CUBETIQ_TWILIO_ID, ConfigUtils.getProperty(CUBETIQ_TWILIO_ID)) ?: throw NullPointerException("CUBETIQ_TWILIO_ID is required!")
    }

    @JvmStatic
    fun getAccountSender(): String {
        return ConfigUtils.getEnv(CUBETIQ_TWILIO_SENDER, ConfigUtils.getProperty(CUBETIQ_TWILIO_SENDER)) ?: throw NullPointerException("CUBETIQ_TWILIO_SENDER is required for SMS Sender!")
    }
}