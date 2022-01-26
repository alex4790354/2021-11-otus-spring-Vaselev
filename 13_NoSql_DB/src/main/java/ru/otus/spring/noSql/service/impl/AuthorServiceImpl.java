package ru.otus.spring.noSql.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.spring.noSql.domain.Author;
import ru.otus.spring.noSql.repositories.AuthorRepository;
import ru.otus.spring.noSql.service.AuthorService;
import java.util.List;


@Component
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @Override
    public String create(String fullName) {
        Author author = new Author(fullName);
        return authorRepository.save(author).getId();
    }

    @Override
    public void update(String oldName, String name) {
        Author author = authorRepository.findByName(oldName).orElseThrow();
        author.setName(name);
        authorRepository.save(author);
    }

    @Override
    public Author getByName(String name) {
        Author author = authorRepository.findById(name).orElseThrow();
        return author;
    }

    @Override
    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    @Override
    public void delete(String fullName) {
        Author author = authorRepository.findByName(fullName).orElseThrow();
        authorRepository.delete(author);
    }
}
