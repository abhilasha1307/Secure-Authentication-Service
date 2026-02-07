package com.abhilasha.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication(
        exclude = {
                DataSourceAutoConfiguration.class
        }
)
public class SecureAuthServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecureAuthServiceApplication.class, args);
	}

}
