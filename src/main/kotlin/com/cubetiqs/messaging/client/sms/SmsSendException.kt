package com.cubetiqs.messaging.client.sms

open class SmsSendException : RuntimeException {
    constructor(message: String? = "Sms send occurred!") : super(message)
    constructor(message: String?, cause: Throwable?) : super(message, cause)
    constructor(cause: Throwable?) : super(cause)
}