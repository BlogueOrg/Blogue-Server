package com.blogue.blogue.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum Status {

    MEMBER_CREATED(HttpStatus.CREATED, "회원 생성이 완료되었습니다.");

    private HttpStatus httpStatus;
    private String statusMessage;
}
