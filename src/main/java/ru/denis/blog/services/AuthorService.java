package ru.denis.blog.services;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.denis.blog.jwts.JwtProvider;
import ru.denis.blog.models.Author;
import ru.denis.blog.repositories.AuthorRepository;

import java.util.Arrays;
import java.util.Optional;

@Service
public class AuthorService implements UserDetailsService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private JwtProvider jwtProvider;

    public Author getById(Long id) {
        if(id != -1) {
            return authorRepository.getReferenceById(id);
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
