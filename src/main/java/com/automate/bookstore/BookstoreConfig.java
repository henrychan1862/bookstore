package com.automate.bookstore;

import com.automate.bookstore.security.SecurityConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


/**
 * enable configuration for web security and invoke jpa to take care of temporal field in entities
 */
@Configuration
@EnableWebSecurity
@EnableJpaAuditing  // cannot put at Application Entry Point otherwise will halt tests
@Import(SecurityConfig.class)
public class BookstoreConfig {
}
