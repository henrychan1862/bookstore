package com.automate.bookstore.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/books/**").permitAll()
                        .requestMatchers("/customers/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/orders/**").hasAnyRole("USER", "ADMIN")
                        .anyRequest().denyAll())
                .formLogin(withDefaults())
                .httpBasic(withDefaults())
                .csrf(CsrfConfigurer::disable);

        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {

        UserDetails user = User.withUsername("peter").password(passwordEncoder.encode("peter")).roles("USER").build();
        UserDetails henry = User.withUsername("henry").password(passwordEncoder.encode("henry")).roles("USER").build();
        UserDetails admin = User.withUsername("admin").password(passwordEncoder.encode("admin")).roles("USER", "ADMIN").build();

        return new InMemoryUserDetailsManager(user, admin, henry);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}

