package com.cubetiqs.messaging.client.util

import com.cubetiqs.util.TextFormat

fun String?.textFormat(): TextFormat? {
    this ?: return null
    return TextFormat(this)
}

fun String?.textFormat(vararg args: Any?): String? {
    return this.textFormat()?.format(*args)
}