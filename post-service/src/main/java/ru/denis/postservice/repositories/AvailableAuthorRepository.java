package ru.denis.postservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.denis.postservice.models.AvailableAuthor;

@Repository
public interface AvailableAuthorRepository extends JpaRepository<AvailableAuthor, Long> {
    AvailableAuthor findByEmail(String email);
}
