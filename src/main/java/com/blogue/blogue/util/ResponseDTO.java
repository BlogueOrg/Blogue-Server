package com.blogue.blogue.util;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDTO <T> {
    private Integer statusCode;
    private String statusMessage;
    private T data;
}
