package com.jdc.payroll.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.jdc.payroll.security.service.SecretKeys;

public class SecretKeysTest {

	@Test
	void test() {
		
		var key = SecretKeys.getKey();
		var value1 = SecretKeys.encode(key);
		
		var key2 = SecretKeys.decode(value1);
		var value2 = SecretKeys.encode(key2);
		
		System.out.println(value1);
		System.out.println(value2);
		
		assertEquals(value1, value2);
	}
}
