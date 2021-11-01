package com.spring.text;

public class TextPrinterTest {
    public static void main(String[] args) {
//        TextPrinter textPrinter = new TextPrinter(new TextConverterImpl());
        TextPrinter textPrinter = new TextPrinter(new TextConverterLower());

        textPrinter.print("TEAR");
    }
}
;