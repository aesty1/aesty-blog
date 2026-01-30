package ru.denis.blog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.denis.blog.dtos.CommentDto;
import ru.denis.blog.dtos.PostDto;
import ru.denis.blog.models.Author;
import ru.denis.blog.models.Comment;
import ru.denis.blog.models.Post;
import ru.denis.blog.repositories.AuthorRepository;
import ru.denis.blog.repositories.PostRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public Post getById(Long id) {
        return postRepository.getReferenceById(id);
    }

    public List<Post> getAll() {
        return postRepository.findAll();
    }

    public List<Post> getAllByAuthor(Author author) {
        return postRepository.findAllByAuthor(author);
    }

    public void save(PostDto postDto) {
        Post post = new Post();

        post.setAuthor(authorRepository.getReferenceById(postDto.getAuthorId()));
        post.setContent(postDto.getContent());
        post.setTags(postDto.getTags());
        post.setTitle(postDto.getTitle());

        postRepository.save(post);
    }

    public void delete(Long id) {
        postRepository.deleteById(id);
    }

    public void edit(PostDto postDto) {
        Post post = new Post();

        post.setAuthor(authorRepository.getReferenceById(postDto.getAuthorId()));
        post.setContent(postDto.getContent());
        post.setId(postDto.getId());
        post.setCreatedAt(postDto.getCreatedAt());
        post.setTags(postDto.getTags());
        post.setTitle(postDto.getTitle());
        post.setComments(postDto.getCommentDtos().stream()
                .map(this::convertDTO)
                .collect(Collectors.toList()));

        postRepository.save(post);
    }

    private Comment convertDTO(CommentDto dto) {
        Comment comment = new Comment();
        comment.setId(dto.getId());
        comment.setContent(dto.getContent());
        comment.setCreatedAt(dto.getCreatedAt());
        comment.setPost(postRepository.getReferenceById(dto.getPostId()));
        comment.setNickname(dto.getNickname());

        return comment;
    }
}
