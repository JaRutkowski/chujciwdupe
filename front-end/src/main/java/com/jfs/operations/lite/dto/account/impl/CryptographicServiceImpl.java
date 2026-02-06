package com.jfs.operations.lite.dto.account.impl;

import com.jfs.operations.lite.dto.account.CryptographicService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;

@Service
public class CryptographicServiceImpl implements CryptographicService {
	@Value("${app.crypto.userPasswordAlgorithm}")
	private String userPasswordAlgorithm;
	@Value("${app.crypto.appPasswordAlgorithm}")
	private String appPasswordAlgorithm;

	@Override
	public String encrypt(String decoded, Key key) throws Exception {
		Cipher c = Cipher.getInstance(userPasswordAlgorithm);
		c.init(Cipher.ENCRYPT_MODE, key);
		byte[] encVal = c.doFinal(decoded.getBytes());
		return Base64.getEncoder().encodeToString(encVal);
	}

	@Override
	public String decrypt(String encoded, Key key) throws Exception {
		Cipher c = Cipher.getInstance(userPasswordAlgorithm);
		c.init(Cipher.DECRYPT_MODE, key);
		byte[] decodedValue = Base64.getDecoder().decode(encoded);
		byte[] decValue = c.doFinal(decodedValue);
		return new String(decValue);
	}

	@Override
	public String calculateSHA512(String text) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance(appPasswordAlgorithm);
		byte[] messageDigest = md.digest(text.getBytes());
		BigInteger no = new BigInteger(1, messageDigest);
		String hash = no.toString(16);
		while (hash.length() < 32)
			hash = "0" + hash;
		return hash;
	}

	@Override
	public Key generateKey(String keyValue) {
		return new SecretKeySpec(keyValue.getBytes(), userPasswordAlgorithm);
	}

	@Override
	public String generateRandomText(int targetStringLength) {
		int leftLimit = 97, rightLimit = 122;
		return new Random().ints(leftLimit, rightLimit + 1)
				.limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
				.toString();
	}
}
