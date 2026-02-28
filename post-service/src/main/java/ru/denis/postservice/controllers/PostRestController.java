package ru.denis.postservice.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import ru.denis.postservice.dtos.PostDto;
import ru.denis.postservice.models.AvailableAuthor;
import ru.denis.postservice.models.Post;
import ru.denis.postservice.services.AvailableAuthorService;
import ru.denis.postservice.services.PostService;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/posts")
public class PostRestController {

    @Autowired
    private PostService postService;

    @Autowired
    private AvailableAuthorService availableAuthorService;

    @PostMapping()
    public ResponseEntity<?> savePost(@RequestBody PostDto postDto, Authentication authentication) {
        AvailableAuthor availableAuthor = availableAuthorService.getByEmail(authentication.getName());

        postDto.setAuthorId(availableAuthor.getId());
        postService.save(postDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(postDto);
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> editPost(@RequestBody PostDto postDto, Authentication authentication) {
        Long id = availableAuthorService.getByEmail(authentication.getName()).getId();

        if(Objects.equals(id, postDto.getAuthorId())) {
            postService.edit(postDto);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(postDto);
        }

        return ResponseEntity
                .status(HttpStatus.BAD_GATEWAY)
                .body("Ошибка в изменении поста");
    }

    @PostMapping("/{id}/delete")
    public ResponseEntity<?> deletePost(@PathVariable Long id, Authentication authentication) {
        AvailableAuthor availableAuthor = availableAuthorService.getByEmail(authentication.getName());
        Post post = postService.getById(id);
        if(post.getAuthor().getId().equals(availableAuthor.getId())) {
            postService.delete(id);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Пост удален");
        } else {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("Не удалось подтвердить автора");
        }

    }

    @GetMapping()
    public ResponseEntity<?> getAll() {
        List<Post> posts = postService.getAll();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(posts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            Post post = postService.getById(id);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(post);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_GATEWAY)
                    .body(e);
        }
    }



}
