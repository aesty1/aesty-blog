package ru.denis.blog.rest_controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import ru.denis.blog.dtos.CommentDto;
import ru.denis.blog.services.CommentService;

@RestController
@RequestMapping("/posts")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/{postId}/comments")
    public RedirectView saveComment(@PathVariable Long postId, @ModelAttribute CommentDto commentDto) {
        commentDto.setPostId(postId);

        commentService.save(commentDto);

        return new RedirectView("/posts/" + postId);
    }
}
