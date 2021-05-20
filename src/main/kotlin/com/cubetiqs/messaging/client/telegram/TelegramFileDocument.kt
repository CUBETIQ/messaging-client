package com.cubetiqs.messaging.client.telegram

import java.io.Serializable

/**
 * File Document
 *
 * @author sombochea
 * @since 1.0
 */
open class TelegramFileDocument (
    var file: ByteArray? = null,
    var name: String? = null,
    var type: String? = null,
    var extension: String? = null,
) : Serializable