package com.jfs.operations.lite.infrastructure.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ExpiredPasswordException extends RuntimeException {
    public ExpiredPasswordException(String message){
        super(message);
    }
}
