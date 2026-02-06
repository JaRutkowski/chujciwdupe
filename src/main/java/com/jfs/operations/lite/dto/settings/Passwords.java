package com.jfs.operations.lite.dto.settings;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Passwords {
    private String newPassword;
    private String confirmPassword;
}
