package com.spring.textTest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfigration {

    @Bean
    public TextConverter textConverter() {
        return new UpperTextConverter();
    }

    @Bean
    public UpperTextConverter upperTextConverter() {
        return new UpperTextConverter();
    }

    @Bean LowerTextConverter lowerTextConverter() {
        return new LowerTextConverter();
    }
}
