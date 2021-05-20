package com.cubetiqs.messaging.client.sms

/**
 * SMS Provider
 *
 * @author sombochea
 * @since 1.0
 */
abstract class SmsProvider : ISmsProvider {
    private var to: String? = null
    private var text: String? = null

    fun getToNumber(): String = to?.trim() ?: throw IllegalArgumentException("Sms receiver is required!")
    fun getText(): String = text ?: throw IllegalArgumentException("Sms content is required!")

    fun setText(text: String?) = apply {
        this.text = text
    }

    fun setToNumber(toNumber: String?) = apply {
        this.to = toNumber
    }
}