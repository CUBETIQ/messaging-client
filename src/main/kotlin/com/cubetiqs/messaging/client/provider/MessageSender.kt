package com.cubetiqs.messaging.client.provider

/**
 * Message Sender
 *
 * @author sombochea
 * @since 1.0
 */
class MessageSender (provider: MessageProvider? = null) {
    private var provider: MessageProvider? = null

    init {
        this.provider = provider
    }

    fun setProvider(provider: MessageProvider) = apply {
        this.provider = provider
    }

    fun send() = provider?.send()

    companion object {
        fun send(provider: MessageProvider): Any? {
            return MessageSender(provider).send()
        }
    }
}