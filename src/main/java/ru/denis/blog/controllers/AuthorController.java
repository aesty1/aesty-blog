package ru.denis.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.denis.blog.models.Author;
import ru.denis.blog.models.Post;
import ru.denis.blog.services.AuthorService;
import ru.denis.blog.services.PostService;

import java.util.List;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private PostService postService;

    @GetMapping("/{id}")
    public String profile(Model model, @PathVariable Long id) {
        Author author = authorService.getById(id);
        List<Post> posts = postService.getAllByAuthor(author);

        int totalComments = posts.stream()
                .mapToInt(post -> post.getComments() != null ? post.getComments().size() : 0)
                .sum();

        model.addAttribute("author", author);
        model.addAttribute("authorPosts", posts);
        model.addAttribute("totalComments", totalComments);

        return "profile";
    }
}
