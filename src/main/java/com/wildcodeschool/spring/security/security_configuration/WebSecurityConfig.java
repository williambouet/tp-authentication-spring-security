package com.wildcodeschool.spring.security.security_configuration;


import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.wildcodeschool.spring.security.persistence.enums.RoleEnum;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	private final String adminRole = RoleEnum.ADMINISTRATOR.name();
	
	public static PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

	@Bean
	public PasswordEncoder passwordEncoder() { 
		return passwordEncoder;
	}

// 	@Bean
// 	public UserDetailsManager userDetailsService() {

//         System.out.println("Initializing UserDetailsService");

// var passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
// 		UserDetails user = User.withUsername("louis")
// 								.password(passwordEncoder.encode("here"))
// 								.roles("USER").build();
// 		UserDetails admin = User.withUsername("admin")
// 								.password(passwordEncoder.encode("hereadmin"))
// 								.roles("ADMIN").build();
// 		return new InMemoryUserDetailsManager(List.of(user, admin));
// 	}


	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        System.out.println("Initializing SecurityFilterChain");

		http
			.authorizeHttpRequests()
				.requestMatchers("/auth**").authenticated()
				.requestMatchers("/auth/admin**").hasAuthority(adminRole)
				.anyRequest().permitAll()
			.and()
				.exceptionHandling().accessDeniedPage("/errorAccessUnAuthorised")
			.and()
				.formLogin()
					.loginPage("/login")
					.defaultSuccessUrl("/auth")
					.failureHandler((HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) -> {
						System.out.println("error during auth");
						exception.printStackTrace();
						response.sendRedirect("/error?error=" + exception.getMessage());
					})
					.usernameParameter("username")
					.passwordParameter("password")
			.and()
				.logout().invalidateHttpSession(true)
				.logoutUrl("/logout")
				.logoutSuccessUrl("/login")
			.and()
				.csrf()
			.and()
				.sessionManagement().maximumSessions(1)
				.expiredUrl("/login");

		return http.build();
	}
}
