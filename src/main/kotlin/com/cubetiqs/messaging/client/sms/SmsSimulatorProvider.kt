package com.cubetiqs.messaging.client.sms

class SmsSimulatorProvider : SmsProvider() {
    private fun getFormatMessage() = """
        To Number: ${getToNumber()}
        Message: ${getText()}
    """.trimIndent()

    override fun send(): Any {
        return getFormatMessage()
    }
}