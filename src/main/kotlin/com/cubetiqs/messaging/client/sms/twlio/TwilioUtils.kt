package com.cubetiqs.messaging.client.sms.twlio

import com.cubetiqs.messaging.client.sms.SmsMessage
import com.cubetiqs.messaging.client.sms.SmsSendException
import com.cubetiqs.messaging.client.sms.twlio.TwilioConfig.ENDPOINT
import com.cubetiqs.messaging.client.util.Loggable
import com.cubetiqs.messaging.client.webclient.WebClientUtils
import java.util.concurrent.atomic.AtomicInteger

object TwilioUtils : Loggable {
    private val limiters = mutableMapOf<String, AtomicInteger>()
    // able to send the sms
    private var capacity = 3

    private fun increaseSentCount(key: String): Int {
        return if (limiters.containsKey(key)) {
            limiters[key]!!.incrementAndGet()
        } else {
            limiters[key] = AtomicInteger(1)
            1
        }
    }

    @JvmStatic
    // reset statistic for key of sms
    fun resetCounter(key: String) {
        if (limiters.containsKey(key)) {
            limiters[key]!!.set(0)
        }
    }

    @JvmStatic
    // reset all statistic
    fun releaseAllCounter() = limiters.clear()

    private var accountId: String? = null
    private var accountToken: String? = null

    @JvmStatic
    fun init(accountId: String, accountToken: String, capacity: Int = 3) {
        log.info("Twilio initializing Account ID: {} and Account Token: {}", accountId, accountToken)

        TwilioUtils.accountId = accountId
        TwilioUtils.accountToken = accountToken
        TwilioUtils.capacity = capacity
    }

    private fun getAccountId(): String {
        return this.accountId ?: TwilioConfig.getAccountId()
    }

    private fun getAccountToken(): String {
        return this.accountToken ?: TwilioConfig.getAccountToken()
    }

    @JvmStatic
    fun capacity(capacity: Int) = apply { TwilioUtils.capacity = capacity }

    private fun getEndpointMessage(): String {
        if (getAccountId().isEmpty() && getAccountToken().isEmpty()) throw IllegalArgumentException("account id and token must be not empty!")
        return "$ENDPOINT/$accountId/Messages.json"
    }

    @JvmStatic
    fun sendMessage(message: SmsMessage): Any {
        if (message.to.isEmpty() || message.to.isBlank()) throw IllegalArgumentException("message send to must be not empty or blank!")
        if (message.sender.isEmpty() || message.sender.isBlank()) throw IllegalArgumentException("message sender must be not empty or blank!")
        if (message.text.isEmpty() || message.text.isBlank()) throw IllegalArgumentException("message must be not empty or blank!")

        if (increaseSentCount(message.to) > capacity) {
            throw SmsSendException("send capacity out of bound!")
        }

        val body: MutableMap<String, String> = mutableMapOf()
        body["To"] = message.to
        body["Body"] = message.text

        if (message.isSenderNumber) {
            body["From"] = message.sender
        } else {
            body["MessagingServiceSid"] = message.sender
        }

        val url = getEndpointMessage()
        val result = WebClientUtils.postRequest(url, body)

        log.info("Twilio Complete sent message via: {}", message)
        return result
    }

    @JvmStatic
    fun sendMessage(sendTo: String, message: String): Any {
        val request = SmsMessage.builder()
            .setText(message)
            .setSender(TwilioConfig.getAccountSender())
            .setTo(sendTo)
            .build()

        return sendMessage(request)
    }
}