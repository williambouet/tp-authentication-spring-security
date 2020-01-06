package com.wildcodeschool.spring.security.persistence.repositories;

import com.wildcodeschool.spring.security.persistence.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
}