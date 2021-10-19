package com.spring.textTest;

// print의 관심만을 가짐
public class TextPrinter {

    TextConverter textConverter;

    public TextPrinter(TextConverter textConverter) {
        this.textConverter = textConverter;
    }

    public void print(String message) {
        System.out.println(textConverter.convert(message));
    }
}
