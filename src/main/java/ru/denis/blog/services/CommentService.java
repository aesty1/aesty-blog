package ru.denis.blog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.denis.blog.dtos.CommentDto;
import ru.denis.blog.models.Comment;
import ru.denis.blog.repositories.CommentRepository;
import ru.denis.blog.repositories.PostRepository;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    public Comment getById(Long id) {
        return commentRepository.getReferenceById(id);
    }

    public void save(CommentDto commentDto) {
        Comment comment = new Comment();

        comment.setContent(commentDto.getContent());
        comment.setNickname(commentDto.getNickname());
        comment.setPost(postRepository.getReferenceById(commentDto.getPostId()));

        commentRepository.save(comment);
    }
}
