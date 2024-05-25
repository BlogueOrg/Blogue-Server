package com.blogue.blogue.util;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ListResultResponse<T> {
    private int count;
    private T data; // 리스트는 데이터 필드 값으로
}

