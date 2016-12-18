package com.saanx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConfiguratorApplication {

	public static final String PROFILE_DEV = "dev";
	public static final String PROFILE_PROD = "production";

	public static void main(String[] args) {
		SpringApplication.run(ConfiguratorApplication.class, args);
	}
}
