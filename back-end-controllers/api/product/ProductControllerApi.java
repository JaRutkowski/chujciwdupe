package com.jfs.operations.lite.controller.api.product;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/api/product", produces = MediaType.APPLICATION_JSON_VALUE)
public interface ProductControllerApi {
	@GetMapping("/table")
	ResponseEntity findAllTableProducts();
}
