package com.blogue.blogue.util;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.ResponseEntity;

@Data
@Builder
public class ResponseDTO <T> {
    private Integer statusCode;
    private String statusMessage;
    @JsonIgnore
    private Status status;
    private T body = null;

    public ResponseDTO(Status status, final T body) {
        this.status = status;
        this.body = body;
    }

    public ResponseDTO(Status status) {
        this.status = status;
    }

    public ResponseDTO(Integer statusCode, String statusMessage, Status status, T body) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.status = status;
        this.body = body;
    }

    public ResponseEntity returnResponseEntity() {
        return ResponseEntity
                .status(status.getHttpStatus())
                .body(ResponseDTO.builder()
                        .statusCode(status.getHttpStatus().value())
                        .statusMessage(status.getStatusMessage())
                        .body(body)
                        .build()
                );
    }
}
