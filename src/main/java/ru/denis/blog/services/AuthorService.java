package ru.denis.blog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.denis.blog.models.Author;
import ru.denis.blog.repositories.AuthorRepository;

@Service
public class AuthorService implements UserDetailsService {

    @Autowired
    private AuthorRepository authorRepository;

    public Author getById(Long id) {
        return authorRepository.getReferenceById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Author author = authorRepository.findAuthorByEmail(email).orElse(null);

        if(author != null) {
            return User.builder()
                    .username(author.getEmail())
                    .password(author.getPassword())
                    .build();
        } else {
            throw new UsernameNotFoundException(email);
        }
    }

    public void save(Author author) {
        authorRepository.save(author);
    }
}
