package ru.denis.blog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.denis.blog.models.Comment;
import ru.denis.blog.repositories.CommentRepository;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public Comment getById(Long id) {
        return commentRepository.getReferenceById(id);
    }
}
