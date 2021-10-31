package com.spring.user.dao.calculator;

public interface LineCallback<T> {
    T doSomethingWithLine(String line, T value);
}
