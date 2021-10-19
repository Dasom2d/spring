package com.spring.text;

public class TextConverterImpl implements TextConverter{


    @Override
    public String convert(String message) {
        return message.toUpperCase();
    }
}
