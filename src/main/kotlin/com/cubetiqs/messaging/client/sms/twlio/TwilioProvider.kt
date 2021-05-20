package com.cubetiqs.messaging.client.sms.twlio

import com.cubetiqs.messaging.client.sms.SmsMessage
import com.cubetiqs.messaging.client.sms.SmsProvider

open class TwilioProvider (private val message: SmsMessage) : SmsProvider() {
    override fun send(): Any? {
        return TwilioUtils.sendMessage(message)
    }
}