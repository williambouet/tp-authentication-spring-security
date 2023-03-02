package com.wildcodeschool.spring.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class ExerciseApplication {
    private static Logger logger = LoggerFactory.getLogger(ExerciseApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ExerciseApplication.class, args);
	}

	@EventListener
	public void onStarted(ApplicationStartedEvent event) {
		logger.info("application started");

		// 2. Initialisation des données
	}
}
