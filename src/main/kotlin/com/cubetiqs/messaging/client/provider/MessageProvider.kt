package com.cubetiqs.messaging.client.provider

@FunctionalInterface
fun interface MessageProvider {
    fun send(): Any?
}