package com.spring.textTest;

public class LowerTextConverter implements TextConverter{

    @Override
    public String convert(String message) {
        return message.toLowerCase();
    }
}
