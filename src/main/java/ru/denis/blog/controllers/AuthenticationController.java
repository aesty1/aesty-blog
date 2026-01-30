package ru.denis.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.denis.blog.dtos.forms.LoginForm;
import ru.denis.blog.models.Author;

@Controller
public class AuthenticationController {

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
}
