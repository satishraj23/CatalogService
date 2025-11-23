package com.ctw.catalog;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ctw.catalog.filters.JWTFilter;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filter(HttpSecurity httpSecurity,JWTFilter jwtFilter) throws Exception {

		httpSecurity.authorizeHttpRequests(auth -> auth
				.requestMatchers("/api/products/*")
				.permitAll().anyRequest().authenticated())
				.httpBasic(basic -> basic.disable()).formLogin(form -> form.disable())
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

		return httpSecurity.build();
	}

}
