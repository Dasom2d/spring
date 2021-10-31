package com.spring.user.dao.calculator;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import java.io.IOException;

public class CalSumTest {

    @Test
    public void sumOfNumbers() throws IOException {
        Calculator calculator = new Calculator();
        int sum = calculator.calcSum(getClass().getResource("numbers.txt").getPath());
        assertThat(sum, is(10));

        int result = calculator.calcMultiply(getClass().getResource("numbers.txt").getPath());
        assertThat(result, is(24));
    }
}
