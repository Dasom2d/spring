package com.spring.user.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class ListReaderByTemplate {
    public List<String> getList() {
        // DB에서 조회
        List<String> rawList = new ArrayList<>();
        return rawList.stream()
                .map(this::handleItem)
                .collect(Collectors.toList());
    }

    protected abstract String handleItem(String rawItem);
}
