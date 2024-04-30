package com.banque.banquemisr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EntityScan("com.banque.banquemisr")
@EnableScheduling
@EnableAsync
public class BanqueMisrApplication {

	public static void main(String[] args) {
		SpringApplication.run(BanqueMisrApplication.class, args);
	}

}
