package ru.denis.blog.rest_controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import ru.denis.blog.dtos.PostDto;
import ru.denis.blog.services.AuthorService;
import ru.denis.blog.services.PostService;

import java.util.Objects;

@RestController
@RequestMapping("/posts")
public class PostRestController {

    @Autowired
    private PostService postService;

    @Autowired
    private AuthorService authorService;

    @PostMapping()
    public RedirectView savePost(@ModelAttribute PostDto postDto, HttpServletRequest request) {
        postDto.setAuthorId(authorService.getCurrentAuthorIdByRequest(request));
        postService.save(postDto);

        return new RedirectView("/");
    }

    @PostMapping("/{id}")
    public RedirectView editPost(@ModelAttribute PostDto postDto, HttpServletRequest request) {
        Long id = authorService.getCurrentAuthorIdByRequest(request);

        if(Objects.equals(id, postDto.getAuthorId())) {
            postService.edit(postDto);
        }

        return new RedirectView("/");
    }

    @PostMapping("/{id}/delete")
    public RedirectView deletePost(@PathVariable Long id) {
        postService.delete(id);

        return new RedirectView("/");
    }
}
