package com.jfs.operations.lite.controller.api.product.impl;

import com.jfs.operations.lite.controller.api.product.ProductControllerApi;
import com.jfs.operations.lite.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductController implements ProductControllerApi {
	private final ProductService productService;

	@Override
	public ResponseEntity findAllTableProducts() {
		return ResponseEntity.ok(productService.findAllTableProducts());
	}
}
