package ru.denis.blog.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.denis.blog.dtos.CommentDto;
import ru.denis.blog.dtos.PostDto;
import ru.denis.blog.models.Comment;
import ru.denis.blog.models.Post;
import ru.denis.blog.services.AuthorService;
import ru.denis.blog.services.PostService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private AuthorService authorService;

    @GetMapping()
    public String homePage(Model model, HttpServletRequest request) {
        List<Post> posts = postService.getAll();
        model.addAttribute("posts", posts);
        model.addAttribute("author", authorService.getById(authorService.getCurrentAuthorIdByRequest(request)));

        return "homePage";
    }

    @GetMapping("/posts/{id}")
    public String postPage(Model model, @PathVariable Long id, HttpServletRequest request) {
        Post post = postService.getById(id);

        model.addAttribute(post);
        model.addAttribute(new CommentDto());
        model.addAttribute("author", authorService.getById(authorService.getCurrentAuthorIdByRequest(request)));

        return "post";
    }

    @GetMapping("/posts/new")
    public String newPostPage(Model model, HttpServletRequest request) {
        model.addAttribute(new PostDto());
        model.addAttribute("author", authorService.getById(authorService.getCurrentAuthorIdByRequest(request)));

        return "newPost";
    }

    @GetMapping("/posts/{id}/edit")
    public String editPostPage(Model model, @PathVariable Long id, HttpServletRequest request) {
        Post post = postService.getById(id);

        if(!Objects.equals(post.getAuthor().getId(), authorService.getCurrentAuthorIdByRequest(request))) {
            return "redirect:/";
        }

        // Создаем PostDto и заполняем данными из поста
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setTags(post.getTags());
        postDto.setCreatedAt(post.getCreatedAt());
        postDto.setAuthorId(post.getAuthor().getId());


        model.addAttribute("post", post);
        model.addAttribute("postDto", postDto); // Теперь с заполненными данными
        model.addAttribute("author", authorService.getById(authorService.getCurrentAuthorIdByRequest(request)));
        // model.addAttribute("authors", authorService.getAllAuthors()); // если нужен список авторов

        return "editPost";
    }

    private CommentDto convertToDTO(Comment comment) {
        CommentDto dto = new CommentDto();
        dto.setId(comment.getId());
        dto.setContent(comment.getContent());
        dto.setCreatedAt(comment.getCreatedAt());
        dto.setPostId(comment.getPost().getId());
        dto.setNickname(comment.getNickname());

        return dto;
    }
}
