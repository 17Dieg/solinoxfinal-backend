package com.tanque.agua.exception;

import lombok.Getter;

@Getter
public class RequestException extends RuntimeException {
    private final Integer code;

    public RequestException(Integer code, String message) {
        super(message);
        this.code = code;
    }

}
