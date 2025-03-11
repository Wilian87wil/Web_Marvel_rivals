package com.wilian.test.marvel_rivals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		System.out.println("Valor de la variable: " + System.getenv("GOOGLE_CLIENT_ID"));


	}

}
