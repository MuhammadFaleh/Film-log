package com.dev.filmlog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class FilmlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilmlogApplication.class, args);
	}

}
