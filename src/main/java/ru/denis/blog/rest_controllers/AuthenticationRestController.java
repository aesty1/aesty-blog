package ru.denis.blog.rest_controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import ru.denis.blog.dtos.forms.LoginForm;
import ru.denis.blog.jwts.JwtProvider;
import ru.denis.blog.models.Author;
import ru.denis.blog.services.AuthorService;

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
    public RedirectView login(@ModelAttribute LoginForm loginForm, HttpServletResponse response) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginForm.email(),
                loginForm.password()
        ));

        if(authentication.isAuthenticated()) {
            String jwtToken = jwtProvider.createToken(authorService.loadUserByUsername(loginForm.email()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            Cookie cookie = new Cookie("JWT_TOKEN", jwtToken);
            cookie.setHttpOnly(true);
            cookie.setSecure(true);
            cookie.setPath("/");

            response.addCookie(cookie);

            return new RedirectView("/");
        } else {
            throw new UsernameNotFoundException(loginForm.email());
        }
    }

    @PostMapping("/register")
    public RedirectView register(@ModelAttribute Author author) {
        author.setPassword(passwordEncoder.encode(author.getPassword()));

        authorService.save(author);

        return new RedirectView("/login");
    }
}
