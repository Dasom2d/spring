package com.spring.user.calculator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileReadTemplate {

    public <T> T lineReadTemplate(String filePath, LineCallback<T> lineCallback, T initVal) throws IOException {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(filePath));
            T res = initVal;
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                res = lineCallback.doSomethingWithLine(line, res);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if(bufferedReader != null)  {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return null;
    }


    public Integer readTemplate(String filePath, BufferedReaderCallback callback) {
        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new FileReader(filePath));
            return callback.executeCalc(bufferedReader);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if(bufferedReader != null)  {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return null;
    }
}
