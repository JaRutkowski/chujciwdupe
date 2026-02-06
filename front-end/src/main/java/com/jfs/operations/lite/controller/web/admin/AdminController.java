package com.jfs.operations.lite.controller.web.admin;

import com.jfs.operations.lite.controller.web.utils.RestClient;
import com.jfs.operations.lite.dto.admin.Config;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/web/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final RestClient restClient;

    @GetMapping("/version")
    public ResponseEntity<Map<String, String>> version() {
        return ResponseEntity.ok((Map<String, String>) restClient.getSync("/api/admin/version", Map.class));
    }

    @PostMapping("/reload-data")
    public ResponseEntity<Map<String, Config>> feedData() {
        return ResponseEntity.ok((Map<String, Config>) restClient.putSync("/api/admin/reload-data", Map.class));
    }
}
