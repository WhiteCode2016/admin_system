package com.white;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@ServletComponentScan
public class AdminSystemApplication {

	private static final Logger logger = LoggerFactory.getLogger(AdminSystemApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AdminSystemApplication.class, args);
		logger.info("Spring Boot is Running!");
	}
}
