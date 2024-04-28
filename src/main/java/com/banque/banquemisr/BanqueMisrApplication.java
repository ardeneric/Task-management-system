package com.banque.banquemisr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.banque.banquemisr")
public class BanqueMisrApplication {

	public static void main(String[] args) {
		SpringApplication.run(BanqueMisrApplication.class, args);
	}

}
