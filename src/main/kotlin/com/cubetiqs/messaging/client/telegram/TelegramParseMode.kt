package com.cubetiqs.messaging.client.telegram

enum class TelegramParseMode (val mode: String) {
    MARKDOWN("markdown"),
    MARKDOWN_V2("markdownv2"),
    HTML("html"); // Not support for Okhttp
}