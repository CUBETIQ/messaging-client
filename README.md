# CUBETIQ Messaging Client
- [x] Message Provider
- [x] Telegram
- [ ] SMS

# How-to-use
- Environment
```text
CUBETIQ_TELEGRAM_TOKEN: Telegram Bot Token
CUBETIQ_TELEGRAM_RECEIVER: Telegram Chat Id to receiver the message
```

# Example
- Kotlin
```kotlin
package com.cubetiqs.example

import com.cubetiqs.messaging.client.telegram.TelegramBotUtils
import com.cubetiqs.messaging.client.telegram.TelegramConfig
import com.cubetiqs.messaging.client.telegram.TelegramProvider
import org.junit.jupiter.api.Test
import java.io.File

class TelegramExampleKotlinTests {
    private val token = TelegramConfig.POS_SYSTEM_ID
    private val chatId = "-360594386"

    @Test
    fun sendMessage() {
        val text = "Hello World"
        TelegramBotUtils.sendMessage(
            chatId = chatId,
            token = token,
            text = text,
        )
    }

    @Test
    fun sendDocument() {
        val text = "My document caption"
        TelegramBotUtils.sendDocument(
            chatId = chatId,
            token = token,
            text = text,
            filename = "my exam paper.png",
            document = File("src/main/resources/cubetiq.png").readBytes(),
        )
    }

    @Test
    fun sendMessageProvider() {
        val text = "Hello World from Provider"
        TelegramProvider.sendMessage(
            chatId = chatId,
            token = token,
            text = text,
        )
    }
}
```
- Java
```java
package com.cubetiqs.example;

import com.cubetiqs.messaging.client.telegram.TelegramBotUtils;
import com.cubetiqs.messaging.client.telegram.TelegramConfig;
import com.cubetiqs.messaging.client.telegram.TelegramProvider;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public final class TelegramExampleJavaTests {
    private final String token = TelegramConfig.getToken();
    private final String chatId = TelegramConfig.getReceiver();

    @Test
    public void sendMessage() {
        String text = "Hello, Sambo!";
        TelegramBotUtils.sendMessage(
                chatId,
                text,
                token
        );
    }

    @Test
    public void sendDocument() throws IOException {
        String text = "Hello, Sambo with my paper!";
        File file = new File("src/main/resources/cubetiq.png");
        TelegramBotUtils.sendDocument(
                chatId,
                text,
                "my paper.png",
                Files.readAllBytes(file.toPath()),
                token
        );
    }

    @Test
    public void sendMessageProvider() {
        String text = "Hello, Sambo with Provider!";
        TelegramProvider.sendMessage(
                chatId,
                text,
                token
        );
    }
}
```
# Contributors
- Sambo Chea <sombochea@cubetiqs.com>