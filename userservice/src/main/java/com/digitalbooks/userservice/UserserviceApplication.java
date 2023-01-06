package com.digitalbooks.userservice;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@SpringBootApplication
@EnableFeignClients
@SecurityScheme(
		name = "demo_security_scheme", 
		scheme = "bearer", 
		type = SecuritySchemeType.HTTP, 
		in = SecuritySchemeIn.HEADER
	)
public class UserserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserserviceApplication.class, args);
	}
	
	@Bean
	public static ModelMapper getModelMapper() {
		return new ModelMapper();
	}
	
	@Bean
	public static RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

}
