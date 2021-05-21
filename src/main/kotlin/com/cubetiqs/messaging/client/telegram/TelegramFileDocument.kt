package com.cubetiqs.messaging.client.telegram

import java.io.Serializable

/**
 * Telegram File Document
 *
 * @author sombochea
 * @since 1.0
 */
open class TelegramFileDocument (
    var file: ByteArray? = null,
    var name: String? = null,
    var type: String? = null,
    var extension: String? = null,
) : Serializable {
    fun setFile(file: ByteArray?) = apply {
        this.file = file
    }

    fun setName(name: String?) = apply {
        this.name = name
    }

    fun setType(type: String?) = apply {
        this.type = type
    }

    fun setExtension(extension: String?) = apply {
        this.extension = extension
    }

    companion object {
        fun create() = TelegramFileDocument()
    }
}