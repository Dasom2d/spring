package com.spring.text;

import java.util.Locale;

public class TextConverterLower implements TextConverter{
    @Override
    public String convert(String message) {
        return message.toLowerCase();
    }
}
