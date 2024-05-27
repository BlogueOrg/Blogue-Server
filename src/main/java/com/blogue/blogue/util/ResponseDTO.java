package com.blogue.blogue.util;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDTO <T> {
    private Integer statusCode;
    private String statusMessage;
    private T data;

    public ResponseDTO (Status status, T data) {
        statusCode = status.getStatusCode().value();
        statusMessage = status.getStatusMessage();
        this.data = data;
    }
}
