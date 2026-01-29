package ru.denis.blog.dtos;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDto {

    private Long id;
    private String nickname;
    private String email;
    private String password;
    private LocalDateTime createdAt;
}
