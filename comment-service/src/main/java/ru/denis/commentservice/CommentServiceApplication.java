package ru.denis.commentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication(scanBasePackages = {
        "ru.denis.commentservice",
        "ru.denis.common",
        "ru.denis.postservice"
})
@EnableWebSecurity
@EnableJpaRepositories(basePackages = "ru.denis")
@EntityScan("ru.denis")
public class CommentServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommentServiceApplication.class, args);
    }

}
