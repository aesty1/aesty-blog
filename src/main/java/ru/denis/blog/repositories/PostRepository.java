package ru.denis.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.denis.blog.models.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
