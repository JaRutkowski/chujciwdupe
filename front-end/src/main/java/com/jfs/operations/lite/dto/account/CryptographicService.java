package com.jfs.operations.lite.dto.account;

import java.security.Key;
import java.security.NoSuchAlgorithmException;

public interface CryptographicService { //TODO: #12 to be deleted
	String encrypt(String decoded, Key key) throws Exception;

	String decrypt(String encoded, Key key) throws Exception;

	String calculateSHA512(String text) throws NoSuchAlgorithmException;

	Key generateKey(String keyValue);

	String generateRandomText(int targetStringLength);
}
