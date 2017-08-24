package com.white;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;

@EnableTransactionManagement // 开启注解事务管理，等同于xml配置文件中的 <tx:annotation-driven />
@SpringBootApplication
@ServletComponentScan
public class AdminSystemApplication {

	private static final Logger logger = LoggerFactory.getLogger(AdminSystemApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AdminSystemApplication.class, args);
		logger.info("Spring Boot is Running!");
	}
}
