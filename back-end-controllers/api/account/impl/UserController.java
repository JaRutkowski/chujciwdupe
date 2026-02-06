package com.jfs.operations.lite.controller.api.account.impl;

import com.jfs.operations.lite.controller.api.account.UserControllerApi;
import com.jfs.operations.lite.service.account.KeycloakUserService;
import com.jfs.operations.lite.service.account.impl.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.LoginException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController implements UserControllerApi {
	private final KeycloakUserService keycloakUserService;
	private final UserDetailsServiceImpl userDetailsService;

	@Override
	public ResponseEntity<UserDetails> findByLogin(String login) {
		return ResponseEntity.ok(Optional.ofNullable(userDetailsService.loadUserByUsername(login))
				.orElseThrow(() -> new UsernameNotFoundException("User not present")));
	}

	@Override
	public ResponseEntity create(String login, String password) throws LoginException, NoSuchAlgorithmException {
		userDetailsService.createUser(login, password);
		return ResponseEntity.ok().build();
	}


	public ResponseEntity createRealm(String id, String realm) {
		return ResponseEntity.ok(keycloakUserService.createRealm(id, realm));
	}

	public ResponseEntity createUser(String realm, String username,
	                                 String firstName, String lastName, String email) {
		return ResponseEntity.ok(keycloakUserService.createUser(realm, username, firstName, lastName, email));
	}
}
