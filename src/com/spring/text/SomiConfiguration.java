package com.spring.text;

public class SomiConfiguration {
    @Bean
    public TextConverter upperCaseConverter() {
        System.out.println("upperCaseConverter 실행");
        return new TextConverterImpl();
    }

    @Bean
    public TextConverter lowerCaseConverter() {
        System.out.println("lowerCaseConverter 실행");
        return new TextConverterLower();
    }

    public SomiConfiguration() {
        System.out.println("생성자를 실행합니다.");
    }
}
