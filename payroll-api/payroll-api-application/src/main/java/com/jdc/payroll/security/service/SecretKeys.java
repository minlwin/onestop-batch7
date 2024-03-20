package com.jdc.payroll.security.service;

import java.util.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import io.jsonwebtoken.Jwts;

public class SecretKeys {

	public static SecretKey getKey() {
		return Jwts.SIG.HS512.key().build();
	}
	
	public static String encode(SecretKey key) {
		var bytes = key.getEncoded();
		return Base64.getEncoder().encodeToString(bytes);
	}
	
	public static SecretKey decode(String value) {
		var bytes = Base64.getDecoder().decode(value);
		return new SecretKeySpec(bytes, "HmacSHA512");
	}
}
