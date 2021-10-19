package com.spring.text;

public class TextPrinter {
    private TextConverter textConverter;

    public TextPrinter(TextConverter textConverter) {
        this.textConverter = textConverter;
    }


    public void print(String message) {
        System.out.println(textConverter.convert(message));
    }
}
