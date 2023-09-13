package com.xllWhiteReaper.security_v2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SecurityV2Application {

	public static void main(String[] args) {
		SpringApplication.run(SecurityV2Application.class, args);
	}

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
			System.out.println(passwordEncoder.encode("user123"));
			System.out.println(passwordEncoder.encode("admin456"));
		};
	}
}
