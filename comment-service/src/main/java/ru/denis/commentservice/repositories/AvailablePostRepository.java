package ru.denis.commentservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.denis.commentservice.models.AvailablePost;

@Repository
public interface AvailablePostRepository extends JpaRepository<AvailablePost, Long> {
}
