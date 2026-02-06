package com.jfs.operations.lite.controller.api.settings.impl;

import com.jfs.operations.lite.controller.api.settings.SettingsControllerApi;
import com.jfs.operations.lite.service.settings.dto.ChangePasswordRequestDto;
import com.jfs.operations.lite.service.settings.SettingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SettingsController implements SettingsControllerApi {
    private final SettingsService settingsService;
    @Override
    public ResponseEntity<ChangePasswordRequestDto> changePassword(ChangePasswordRequestDto changePasswordRequestDto) throws Exception {
        return ResponseEntity.ok(settingsService.changePassword(changePasswordRequestDto));
    }
}
