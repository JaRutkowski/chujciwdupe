package com.jfs.operations.lite.dto.schedule;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IcsFileDto {
    String fileName;
    byte[] fileContent;
}
