package ru.denis.commentservice.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import ru.denis.commentservice.dtos.CommentDto;
import ru.denis.commentservice.models.Comment;
import ru.denis.commentservice.services.CommentService;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/post/{postId}/")
    public ResponseEntity<?> saveComment(@PathVariable Long postId, @RequestBody CommentDto commentDto) {
        commentDto.setPostId(postId);

        commentService.save(commentDto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commentDto);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<?> getByPost(@PathVariable Long postId) {
        List<Comment> comments = commentService.getAllByPostId(postId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(comments);
    }
}
