package com.cubetiqs.messaging.client.sms

data class SmsMessage (
    var to: String,
    var sender: String,
    var text: String,
    // if this true, meant the sender is phone number (from)
    // else is the message service id for send the message
    var isSenderNumber: Boolean = false,
) {
    class SmsMessageBuilder {
        private var to: String? = null
        private var sender: String? = null
        private var text: String? = null
        private var isSenderNumber: Boolean? = null

        fun setTo(to: String) = apply { this.to = to }
        fun setSender(sender: String) = apply { this.sender = sender }
        fun setText(text: String) = apply { this.text = text }
        fun isSenderNumber(isSenderNumber: Boolean) = apply { this.isSenderNumber = isSenderNumber }

        fun build(): SmsMessage {
            return SmsMessage(
                to = to ?: throw IllegalArgumentException("Receiver is required!"),
                sender = sender ?: throw IllegalArgumentException("Sender is required!"),
                text = text ?: throw IllegalArgumentException("Message is required!"),
                isSenderNumber = isSenderNumber ?: false
            )
        }
    }

    companion object {
        fun builder() = SmsMessageBuilder()
    }
}