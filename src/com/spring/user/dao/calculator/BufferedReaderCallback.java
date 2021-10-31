package com.spring.user.dao.calculator;

import java.io.BufferedReader;
import java.io.IOException;

public interface BufferedReaderCallback {

    int executeCalc(BufferedReader bufferedReader) throws IOException;
}
