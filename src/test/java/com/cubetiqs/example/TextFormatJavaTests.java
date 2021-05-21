package com.cubetiqs.example;

import com.cubetiqs.util.LogUtil;
import com.cubetiqs.util.TextFormat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TextFormatJavaTests {
    @Test
    public void textFormatter() {
        String text = "Hello, I'm {0} {1}";
        String result = TextFormat.withText(text).format("Sambo", "Chea");
        LogUtil.info("Result => {}", result);

        Assertions.assertEquals("Hello, I'm Sambo Chea", result);
    }
}
