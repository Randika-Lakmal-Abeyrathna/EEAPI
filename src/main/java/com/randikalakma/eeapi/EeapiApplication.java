package com.randikalakma.eeapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class EeapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EeapiApplication.class, args);
	}
}
