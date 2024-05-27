package com.blogue.blogue.util;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ResponseDTO <T> {
    private ResponseMessage message;
    private T data;
}
