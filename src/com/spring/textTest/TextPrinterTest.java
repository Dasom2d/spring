package com.spring.textTest;

public class TextPrinterTest {
    public static void main(String[] args) {

//        TextPrinter textPrinter = new TextPrinter(new UpperTextConverter());
        TextPrinter textPrinter = new TextPrinter(new LowerTextConverter());
        textPrinter.print("TEST");

    }
}
