package ru.denis.postservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.denis.postservice.models.AvailableAuthor;
import ru.denis.postservice.models.Post;


import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByAuthor(AvailableAuthor availableAuthor);
}
