package com.blogue.blogue.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseMessage {

    MEMBER_CREATED("회원 생성이 완료되었습니다.");

    private String message;


}
