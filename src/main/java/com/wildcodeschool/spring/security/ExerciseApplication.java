package com.wildcodeschool.spring.security;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;

import com.wildcodeschool.spring.security.persistence.entities.User;
import com.wildcodeschool.spring.security.persistence.enums.RoleEnum;
import com.wildcodeschool.spring.security.persistence.repositories.UserRepository;

@SpringBootApplication
public class ExerciseApplication {
	private static Logger logger = LoggerFactory.getLogger(ExerciseApplication.class);

	@Autowired
	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(ExerciseApplication.class, args);
	}

	@EventListener
	public void onStarted(ApplicationStartedEvent event) {
		logger.info("application started");

		// 2. Initialisation des données
		userRepository.deleteAll();

		User user = new User("user", "user", "Ufirst", "Ulast", List.of(RoleEnum.USER));
		User admin = new User("admin", "admin", "Afirst", "Alast", List.of(RoleEnum.USER, RoleEnum.ADMINISTRATOR));
		User superadmin = new User("superadmin", "superadmin", "SAfirst", "SAlast", List.of(RoleEnum.USER, RoleEnum.ADMINISTRATOR, RoleEnum.SUPER_ADMIN));

		userRepository.saveAll(List.of(user, admin, superadmin));
	}
}
