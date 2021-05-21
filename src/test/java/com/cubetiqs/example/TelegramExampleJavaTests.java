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
                token,
                null
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
                token,
                null
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
