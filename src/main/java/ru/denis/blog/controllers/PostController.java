package ru.denis.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.denis.blog.models.Post;
import ru.denis.blog.services.PostService;

import java.util.List;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping()
    public String homePage(Model model) {
        List<Post> posts = postService.getAll();
        model.addAttribute("posts", posts);

        return "homePage";
    }
}
