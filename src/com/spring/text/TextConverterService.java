package com.spring.text;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.atomic.AtomicInteger;

public class TextConverterService {
    @Autowired
    @Qualifier("lowerCaseConverter")
    private TextConverter textConverter;

    // 변환 횟수
    public AtomicInteger convertCount = new AtomicInteger(0);

    public String convert(String message) {
        convertCount.incrementAndGet();


        ApplicationContext context = new AnnotationConfigApplicationContext();
    //    context.getBean()

        return textConverter.convert(message);
    }
}
