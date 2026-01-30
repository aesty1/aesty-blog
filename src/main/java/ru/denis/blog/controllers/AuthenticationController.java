package ru.denis.blog.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.denis.blog.dtos.forms.LoginForm;
import ru.denis.blog.models.Author;
import ru.denis.blog.services.AuthorService;

@Controller
public class AuthenticationController {

    @Autowired
    private AuthorService authorService;

    @GetMapping("/login")
    public String loginPage(Model model, LoginForm loginForm) {
        model.addAttribute("loginForm", loginForm);

        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("author", new Author());
        return "register";
    }

    @GetMapping("/me")
    public String myProfile(Model model, HttpServletRequest request) {
        model.addAttribute("author", authorService.getById(authorService.getCurrentAuthorIdByRequest(request)));

        return "me";
    }
}
