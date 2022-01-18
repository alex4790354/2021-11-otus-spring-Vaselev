package ru.otus.spring.jpa.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.jpa.customExceptions.DaoException;
import ru.otus.spring.jpa.domain.Author;
import ru.otus.spring.jpa.domain.Book;
import ru.otus.spring.jpa.repositories.AuthorRepository;
import ru.otus.spring.jpa.services.AuthorService;

import java.util.ArrayList;
import java.util.List;


@Component
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private static final String AUTHOR_NOT_EXIST = "no author found by id";

    @Transactional
    @Override
    public long create(String fullName) {
        Author author = new Author(0L, fullName, new ArrayList<Book>());
        return authorRepository.save(author).getId();
    }

    @SneakyThrows
    @Transactional
    @Override
    public void update(long id, String fullName) {
        Author author = authorRepository.findById(id).orElse(null);
        if (author == null) {
            throw new DaoException(AUTHOR_NOT_EXIST, new RuntimeException());
        }
        author.setName(fullName);
        authorRepository.save(author);
    }

    @SneakyThrows
    @Transactional(readOnly = true)
    @Override
    public Author getById(long id) {
        Author author = authorRepository.findById(id).orElse(null);
        if (author != null) {
            return author;
        } else {
            throw new DaoException(AUTHOR_NOT_EXIST, new RuntimeException());
        }
    }


    @Transactional(readOnly = true)
    @Override
    public List<Author> getAll() {
        return authorRepository.findAll();
    }


    @SneakyThrows
    @Transactional
    @Override
    public void delete(long authorId) {
        Author author = authorRepository.findById(authorId).orElse(null);
        if (author == null) {
            throw new DaoException(AUTHOR_NOT_EXIST, new RuntimeException());
        }
        authorRepository.delete(author);
    }
}
