package com.spring.user.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ListReaderByCallback {
    public List<String> getList(Function<String, String> itemHandler) {
        // DB에서 조회
        List<String> rawList = new ArrayList<>();
        return rawList.stream()
                .map(itemHandler::apply)
                .collect(Collectors.toList());
    }
}
