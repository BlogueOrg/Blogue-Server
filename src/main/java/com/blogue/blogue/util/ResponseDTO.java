package com.blogue.blogue.util;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDTO <T> {
    private ResponseMessage message;
    private T data;
}
