package ru.denis.postservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.denis.common.dto.PostEvent;
import ru.denis.postservice.dtos.PostDto;
import ru.denis.postservice.models.AvailableAuthor;
import ru.denis.postservice.models.Post;
import ru.denis.postservice.repositories.AvailableAuthorRepository;
import ru.denis.postservice.repositories.PostRepository;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private AvailableAuthorRepository availableAuthorRepository;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public Post getById(Long id) {
        return postRepository.getReferenceById(id);
    }

    public List<Post> getAll() {
        return postRepository.findAll();
    }

    public List<Post> getAllByAuthor(AvailableAuthor author) {
        return postRepository.findAllByAuthor(author);
    }

    public void save(PostDto postDto) {
        Post post = new Post();

        post.setAuthor(availableAuthorRepository.getReferenceById(postDto.getAuthorId()));
        post.setContent(postDto.getContent());
        post.setTags(postDto.getTags());
        post.setTitle(postDto.getTitle());

        postRepository.save(post);

        kafkaTemplate.send("post-topic", post.getId().toString(), new PostEvent(post.getId(), post.getTitle(), postDto.getAuthorId(), "SAVE"));
    }

    public void delete(Long id) {
        postRepository.deleteById(id);

        kafkaTemplate.send("post-topic", id.toString(), new PostEvent(id, "", -1L, "DELETE"));
    }

    public void edit(PostDto postDto) {
        Post post = new Post();

        post.setAuthor(availableAuthorRepository.getReferenceById(postDto.getAuthorId()));
        post.setContent(postDto.getContent());
        post.setId(postDto.getId());
        post.setCreatedAt(postDto.getCreatedAt());
        post.setTags(postDto.getTags());
        post.setTitle(postDto.getTitle());

        postRepository.save(post);

        kafkaTemplate.send("post-topic", post.getId().toString(), new PostEvent(post.getId(), post.getTitle(), postDto.getAuthorId(), "EDIT"));
    }

}
