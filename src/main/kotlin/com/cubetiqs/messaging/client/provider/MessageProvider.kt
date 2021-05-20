package com.cubetiqs.messaging.client.provider

/**
 * Message Provider
 *
 * @author sombochea
 * @since 1.0
 */
@FunctionalInterface
fun interface MessageProvider {
    fun send(): Any?
}