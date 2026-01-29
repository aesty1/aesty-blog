package ru.denis.blog.rest_controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import ru.denis.blog.dtos.PostDto;
import ru.denis.blog.services.PostService;

@RestController
@RequestMapping("/posts")
public class PostRestController {

    @Autowired
    private PostService postService;

    @PostMapping()
    public RedirectView savePost(@ModelAttribute PostDto postDto) {
        postDto.setAuthorId(1L);
        postService.save(postDto);

        return new RedirectView("/");
    }

    @PostMapping("/{id}")
    public RedirectView editPost(@ModelAttribute PostDto postDto) {
        postService.edit(postDto);

        return new RedirectView("/");
    }

    @PostMapping("/{id}/delete")
    public RedirectView deletePost(@PathVariable Long id) {
        postService.delete(id);

        return new RedirectView("/");
    }
}
