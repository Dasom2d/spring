package com.spring.textTest;

public class UpperTextConverter implements TextConverter {

    @Override
    public String convert(String message) {
        return message.toUpperCase();
    }
}
