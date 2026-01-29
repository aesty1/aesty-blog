package ru.denis.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.denis.blog.models.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
