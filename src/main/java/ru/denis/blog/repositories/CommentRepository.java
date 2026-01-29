package ru.denis.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.denis.blog.models.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
