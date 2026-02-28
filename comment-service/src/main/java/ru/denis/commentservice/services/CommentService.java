package ru.denis.commentservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.denis.commentservice.dtos.CommentDto;
import ru.denis.commentservice.models.AvailablePost;
import ru.denis.commentservice.models.Comment;
import ru.denis.commentservice.repositories.AvailablePostRepository;
import ru.denis.commentservice.repositories.CommentRepository;
import ru.denis.common.dto.CommentEvent;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private AvailablePostRepository availablePostRepository;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public Comment getById(Long id) {
        return commentRepository.getReferenceById(id);
    }

    public List<Comment> getAllByPostId(Long id) {
        AvailablePost post = availablePostRepository.getReferenceById(id);

        return commentRepository.findAllByPost(post);
    }

    public void save(CommentDto commentDto) {
        Comment comment = new Comment();
        AvailablePost post = availablePostRepository.getReferenceById(commentDto.getPostId());

        comment.setContent(commentDto.getContent());
        comment.setNickname(commentDto.getNickname());
        comment.setPost(post);

        commentRepository.save(comment);

        kafkaTemplate.send("comment-topic", comment.getId().toString(), new CommentEvent(comment.getId(), comment.getPost().getId()));
    }
}
