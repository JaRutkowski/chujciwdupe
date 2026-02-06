package com.jfs.operations.lite.controller.api.client.impl;

import com.jfs.operations.lite.controller.api.client.ClientControllerApi;
import com.jfs.operations.lite.service.client.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ClientController implements ClientControllerApi {
	private final ClientService clientService;

	@Override
	public ResponseEntity findAllTableClients() {
		return ResponseEntity.ok(clientService.findAllTableClients());
	}
}
