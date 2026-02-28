package ru.denis.postservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.denis.postservice.models.AvailableAuthor;
import ru.denis.postservice.repositories.AvailableAuthorRepository;

@Service
public class AvailableAuthorService {

    @Autowired
    private AvailableAuthorRepository availableAuthorRepository;

    public AvailableAuthor getById(Long id) {
        return availableAuthorRepository.getReferenceById(id);
    }

    public void save(AvailableAuthor availableAuthor) {
        availableAuthorRepository.save(availableAuthor);
    }

    public void editNickname(Long id, String nickname) {
        availableAuthorRepository.findById(id).ifPresent(author -> {
            author.setNickname(nickname);
            availableAuthorRepository.save(author);
        });
    }

    public void deleteById(Long id) {
        availableAuthorRepository.deleteById(id);
    }

    public AvailableAuthor getByEmail(String email) {
        return availableAuthorRepository.findByEmail(email);
    }
}
