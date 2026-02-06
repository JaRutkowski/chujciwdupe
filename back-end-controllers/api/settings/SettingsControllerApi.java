package com.jfs.operations.lite.controller.api.settings;

import com.jfs.operations.lite.service.settings.dto.ChangePasswordRequestDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping(value = "/v1/api/settings", produces = MediaType.APPLICATION_JSON_VALUE)
public interface SettingsControllerApi {
    @PutMapping("/change-password")
    ResponseEntity<ChangePasswordRequestDto> changePassword(
            @RequestBody @Valid ChangePasswordRequestDto changePasswordRequestDto) throws Exception;
}
