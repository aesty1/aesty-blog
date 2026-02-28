package ru.denis.authorservice.services;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.denis.authorservice.dtos.AuthorDto;
import ru.denis.authorservice.models.Author;
import ru.denis.authorservice.repositories.AuthorRepository;
import ru.denis.common.dto.AuthorEvent;
import ru.denis.common.jwts.JwtProvider;


import java.util.Arrays;
import java.util.Optional;

@Service
public class AuthorService implements UserDetailsService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private JwtProvider jwtProvider;

    public AuthorDto getById(Long id) {
        if(id != -1) {
            Author author = authorRepository.getReferenceById(id);
            AuthorDto authorDto = new AuthorDto();

            authorDto.setId(author.getId());
            authorDto.setCreatedAt(author.getCreatedAt());
            authorDto.setEmail(author.getEmail());
            authorDto.setNickname(author.getNickname());

            return authorDto;
        } else {
            return null;
        }

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Author author = authorRepository.findAuthorByEmail(email).orElse(null);

        if(author != null) {
            return User.builder()
                    .username(author.getEmail())
                    .password(author.getPassword())
                    .build();
        } else {
            throw new UsernameNotFoundException(email);
        }
    }

    public void save(Author author) {
        authorRepository.save(author);
        try {
            kafkaTemplate.send("author-topic", author.getId().toString(),
                    new AuthorEvent(author.getId(), author.getNickname(), author.getEmail(), "SAVE")).get(); // Ждем подтверждения
            System.out.println(">>> МЫ ПНУЛИ КАФКУ УСПЕШНО");
        } catch (Exception e) {
            System.err.println(">>> КАФКА ПОСЛАЛА НАС: " + e.getMessage());
        }
    }

    public Author getUserByEmail(String email) {
        return authorRepository.findAuthorByEmail(email).orElse(null);
    }

    public Long getCurrentAuthorIdByRequest(HttpServletRequest request) {
        Optional<Cookie> cookie = Arrays.stream(request.getCookies())
                .filter(cook -> cook.getName().equals("JWT_TOKEN"))
                .findFirst();

        if(cookie != null && cookie.isPresent()) {
            String email = jwtProvider.extractUsername(cookie.get().getValue());

            return getUserByEmail(email).getId();
        }

        return -1L;
    }
}
