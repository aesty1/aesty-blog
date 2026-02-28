package ru.denis.authorservice.configs;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableWebSecurity
@Configuration
@EnableJpaRepositories
@ComponentScan("ru.denis.authorservice")
public class SecurityConfig {
}
