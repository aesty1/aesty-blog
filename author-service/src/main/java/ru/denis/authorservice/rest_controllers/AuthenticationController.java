package ru.denis.authorservice.rest_controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.denis.authorservice.dtos.AuthorDto;
import ru.denis.authorservice.dtos.forms.LoginForm;
import ru.denis.authorservice.models.Author;
import ru.denis.authorservice.services.AuthorService;


@Controller
public class AuthenticationController {

    @Autowired
    private AuthorService authorService;

    @GetMapping("/me")
    public ResponseEntity<?> myProfile(Authentication authentication) {
        Author author = authorService.getUserByEmail(authentication.getName());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(author);
    }
}
