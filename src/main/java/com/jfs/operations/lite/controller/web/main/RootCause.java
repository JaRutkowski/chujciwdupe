package com.jfs.operations.lite.controller.web.main;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RootCause {
    INVALID_TOTP("Incorrect code"),
    NOT_AUTHORIZED("Not Authorized"),
    CREDENTIALS_INVALID("Account credentials are not valid"),
    INVALID_PASSWORD("Provided password doesn't meet rules."),
    MATCHING_PASSWORD("Old and new passwords match."),
    EXPIRED_PASSWORD("Password expired"),
    INVALID_USERNAME("Provided username exists."),
    INVALID_DATA_TYPE("Required Type is not present"),
    INVALID_RESULT_VALUE("Provided result value is not correct");

    private final String message;
}

