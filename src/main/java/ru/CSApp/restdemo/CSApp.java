package ru.CSApp.restdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Аннотация объединяет несколько других аннотаций, таких как `@Configuration`, `@EnableAutoConfiguration`, и
//// `@ComponentScan`, и обозначает основной класс Spring Boot приложения
@SpringBootApplication
public class CSApp {

	public static void main(String[] args) {
		SpringApplication.run(CSApp.class, args);
	}
}

