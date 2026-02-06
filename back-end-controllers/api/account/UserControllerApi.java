package com.jfs.operations.lite.controller.api.account;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.LoginException;
import java.security.NoSuchAlgorithmException;

@RequestMapping(value = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE)
//@ApiVersion(1)
public interface UserControllerApi {
	@GetMapping
	ResponseEntity<UserDetails> findByLogin(@RequestParam String login);

	@PostMapping
	ResponseEntity create(@RequestParam String login, @RequestParam String password) throws LoginException, NoSuchAlgorithmException;

	@PostMapping("/create-realm")
	ResponseEntity createRealm(@RequestParam String id, @RequestParam String realm);

	@PostMapping("/{realm}/create-user")
	ResponseEntity createUser(@PathVariable String realm, @RequestParam String username,
	                          @RequestParam String firstName, @RequestParam String lastName, @RequestParam String email);
}
