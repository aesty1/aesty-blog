package ru.denis.authorservice.rest_controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import ru.denis.authorservice.dtos.forms.LoginForm;
import ru.denis.common.jwts.JwtProvider;
import ru.denis.authorservice.models.Author;
import ru.denis.authorservice.services.AuthorService;

@RestController
public class AuthenticationRestController {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginForm loginForm) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginForm.email(),
                loginForm.password()
        ));

        if(authentication.isAuthenticated()) {
            String jwtToken = jwtProvider.createToken(authorService.loadUserByUsername(loginForm.email()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(jwtToken);
        } else {
            throw new UsernameNotFoundException(loginForm.email());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Author author) {
        author.setPassword(passwordEncoder.encode(author.getPassword()));

        authorService.save(author);

        return ResponseEntity.ok("Регистрация прошла успешно");
    }
}
