package ru.denis.commentservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.denis.commentservice.models.AvailablePost;
import ru.denis.commentservice.repositories.AvailablePostRepository;

@Service
public class AvailablePostService {

    @Autowired
    private AvailablePostRepository availablePostRepository;

    public void save(AvailablePost post) {
        availablePostRepository.save(post);
    }

    public void editTitle(Long postId, String title) {
        availablePostRepository.findById(postId).ifPresent(post -> {
            post.setTitle(title);
            availablePostRepository.save(post);
        });
    }

    public void deleteById(Long id) {
        availablePostRepository.deleteById(id);
    }
}
