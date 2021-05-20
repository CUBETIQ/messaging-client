package com.cubetiqs.messaging.client.telegram

open class TelegramSendException : RuntimeException {
    constructor(message: String = "Telegram send occurred!") : super(message)
    constructor(message: String?, cause: Throwable?) : super(message, cause)
    constructor(cause: Throwable?) : super(cause)
}