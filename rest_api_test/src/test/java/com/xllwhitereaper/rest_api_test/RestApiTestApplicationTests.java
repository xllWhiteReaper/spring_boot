package com.xllwhitereaper.rest_api_test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RestApiTestApplicationTests {

	// @Disabled
	// @Test
	// public void contextLoads() {
	// boolean bool = true;
	// assertTrue(bool);
	// }

	@Test
	public void myFirstTest() {
		boolean bool = true;
		assertTrue(bool);
	}
}
