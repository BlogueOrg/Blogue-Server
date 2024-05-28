package com.blogue.blogue.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
@Builder
public class ResponseDTO <T> {
    private Integer statusCode;
    private String statusMessage;
    private T data;
    private Status status;

    public ResponseDTO(Status status, T data) {
        this.status = status;
        this.data = data;
    }

    public ResponseDTO(Integer statusCode, String statusMessage, T data, Status status) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.status = status;
        this.data = data;
    }

    public ResponseEntity returnResponseEntity() {
        return ResponseEntity
                .status(status.getHttpStatus())
                .body(ResponseDTO.builder()
                        .statusCode(status.getHttpStatus().value())
                        .statusMessage(status.getStatusMessage())
                        .data(data)
                );
    }
}
