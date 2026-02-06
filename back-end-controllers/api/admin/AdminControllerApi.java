package com.jfs.operations.lite.controller.api.admin;

import com.jfs.operations.lite.service.file.impl.dto.Config;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@RequestMapping(value = "/api/admin", produces = MediaType.APPLICATION_JSON_VALUE)
public interface AdminControllerApi {
    @GetMapping("/version")
    Map<String, String> version();

    @PostMapping("/feed-data")
    ResponseEntity<Map<String, Config>> feedData();

    @PutMapping("/reload-data")
    ResponseEntity<Map<String, Config>> reloadData();

    @GetMapping
    ResponseEntity<String> test();
}
