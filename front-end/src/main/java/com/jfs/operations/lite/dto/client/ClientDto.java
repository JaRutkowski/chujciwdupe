package com.jfs.operations.lite.dto.client;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@Builder
@Getter
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClientDto {
    Integer id;
    String name;
}

