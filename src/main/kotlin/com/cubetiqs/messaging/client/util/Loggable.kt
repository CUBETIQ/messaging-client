package com.cubetiqs.messaging.client.util

import org.slf4j.LoggerFactory

interface Loggable {
    val log get() = LoggerFactory.getLogger(this::class.java)
}