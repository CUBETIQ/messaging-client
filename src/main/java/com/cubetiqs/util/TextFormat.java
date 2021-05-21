package com.cubetiqs.util;

import java.util.stream.IntStream;

public class TextFormat {
    private String text;

    public TextFormat() {}

    public TextFormat(String text) {
        this.text = text;
    }

    public TextFormat setText(String text) {
        this.text = text;
        return this;
    }

    public String format(Object... args) {
        IntStream.range(0, args.length)
                .forEach(idx -> {
                    String replaced = args[idx].toString();
                    if (replaced == null) {
                        replaced = "";
                    }

                    this.text = this.text.replace("{" + idx + "}", replaced);
                });

        return this.text;
    }

    public static TextFormat create() {
        return new TextFormat();
    }

    public static TextFormat withText(String text) {
        return create().setText(text);
    }
}
