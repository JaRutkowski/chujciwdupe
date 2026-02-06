package com.jfs.operations.lite.infrastructure.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MissingCookieException extends RuntimeException {
    public MissingCookieException(String message){
        super(message);
    }
}
