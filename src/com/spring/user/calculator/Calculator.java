package com.spring.user.calculator;

import java.io.BufferedReader;
import java.io.IOException;

public class Calculator {
    FileReadTemplate fileReadTemplate = new FileReadTemplate();

    public Integer calcSum(String filePath) throws IOException {
        LineCallback<Integer> sumCallback = new LineCallback<Integer>() {
            @Override
            public Integer doSomethingWithLine(String line, Integer value) {
                return value + Integer.valueOf(line);
            }
        };
        return fileReadTemplate.lineReadTemplate(filePath, sumCallback, 0);
    }
//    public Integer calcSum(String filePath) {
//        return fileReadTemplate.readTemplate(filePath, new BufferedReaderCallback() {
//            @Override
//            public int executeCalc(BufferedReader bufferedReader) throws IOException {
//                Integer sum = 0;
//                String line = null;
//
//                while((line = bufferedReader.readLine()) != null) {
//                    sum += Integer.valueOf(line);
//                }
//                return sum;
//            }
//        });
//    }

    public Integer calcMultiply(String filePath) {
        return fileReadTemplate.readTemplate(filePath, new BufferedReaderCallback() {
            @Override
            public int executeCalc(BufferedReader bufferedReader) throws IOException {
                Integer sum = 1;
                String line = null;

                while((line = bufferedReader.readLine()) != null) {
                    sum *= Integer.valueOf(line);
                }
                return sum;
            }
        });
    }

}
