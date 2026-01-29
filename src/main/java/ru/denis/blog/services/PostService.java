package ru.denis.blog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.denis.blog.models.Post;
import ru.denis.blog.repositories.PostRepository;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post getById(Long id) {
        return postRepository.getReferenceById(id);
    }

    public List<Post> getAll() {
        return postRepository.findAll();
    }
}
