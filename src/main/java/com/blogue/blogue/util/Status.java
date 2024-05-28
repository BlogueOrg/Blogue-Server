package com.blogue.blogue.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum Status {

    // Member creation
    MEMBER_CREATED(HttpStatus.CREATED, "New member is successfully created"),

    // Get members
    MEMBERS_FETCHED(HttpStatus.OK, "Members are fetched successfully"),

    // Get single
    MEMBER_FETCHED(HttpStatus.OK, "The member is fetched successfully"),

    private HttpStatus httpStatus;
    private String statusMessage;
}
