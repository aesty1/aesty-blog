package ru.denis.authorservice.rest_controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.denis.authorservice.dtos.AuthorDto;
import ru.denis.authorservice.models.Author;
import ru.denis.authorservice.services.AuthorService;

import java.util.List;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping("/{id}")
    public ResponseEntity<?> profile(@PathVariable Long id) {
        AuthorDto author = authorService.getById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(author);
    }
}
