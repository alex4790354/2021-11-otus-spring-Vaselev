package ru.otus.spring.mongoDb.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.mongoDb.domain.Author;
import ru.otus.spring.mongoDb.repository.AuthorRepository;
import ru.otus.spring.mongoDb.services.AuthorService;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public String create(String name) {
        return authorRepository.save(new Author(name)).getId();
    }

    @Override
    public void update(String oldName, String newName) {
        Author author = findByName(oldName);
        author.setName(newName);
        authorRepository.save(author);
    }

    @Override
    public Author findByName(String name) {
        return Optional.ofNullable(authorRepository.findByName(name))
                       .orElseThrow(() -> new RuntimeException(String.format("Author with name '%s' not found", name)));
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public void deleteByName(String name) {
        // TODO: add book delete. Or block if exist.
        authorRepository.deleteByName(name);
    }

}
