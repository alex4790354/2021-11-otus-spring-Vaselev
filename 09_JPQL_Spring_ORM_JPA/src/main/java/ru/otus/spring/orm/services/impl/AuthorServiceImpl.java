package ru.otus.spring.orm.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.orm.customExceptions.DaoException;
import ru.otus.spring.orm.domain.Author;
import ru.otus.spring.orm.repositories.AuthorRepository;
import ru.otus.spring.orm.services.AuthorService;
import java.util.List;


@Component
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private static final String AUTHOR_NOT_EXIST = "no author found by id";

    @Transactional
    @Override
    public long create(String fullName) {
        Author author = new Author(0L, fullName);
        return authorRepository.save(author).getId();
    }

    @SneakyThrows
    @Transactional
    @Override
    public void update(long id, String fullName) {
        Author author = authorRepository.getAuthorById(id).orElse(null);
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
        Author author = authorRepository.getAuthorById(id).orElse(null);
        if (author != null) {
            return author;
        } else {
            throw new DaoException(AUTHOR_NOT_EXIST, new RuntimeException());
        }

    }


    @Transactional(readOnly = true)
    @Override
    public List<Author> getAll() {
        return authorRepository.getAllAuthors();
    }


    @SneakyThrows
    @Transactional
    @Override
    public void delete(long authorId) {
        Author author = authorRepository.getAuthorById(authorId).orElse(null);
        if (author == null) {
            throw new DaoException(AUTHOR_NOT_EXIST, new RuntimeException());
        }
        authorRepository.delete(author);
    }
}
