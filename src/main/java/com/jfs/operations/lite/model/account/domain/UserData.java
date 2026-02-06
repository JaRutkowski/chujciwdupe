package com.jfs.operations.lite.model.account.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserData { // implements UserDetails { //TODO: #12 to be deleted
    Integer id;
    String username;
    String password;
    String totpCode;
    String passwordToken;
    String captchaToken;
    Object captchaAnswer;
    boolean isAuthorized;
}
