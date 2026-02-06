package com.jfs.operations.lite.controller.api.client;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/api/client", produces = MediaType.APPLICATION_JSON_VALUE)
public interface ClientControllerApi {
	@GetMapping("/table")
	ResponseEntity findAllTableClients();
}
