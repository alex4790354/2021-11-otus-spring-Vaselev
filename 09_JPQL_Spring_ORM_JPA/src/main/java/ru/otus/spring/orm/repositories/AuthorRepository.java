package ru.otus.spring.orm.repositories;


import ru.otus.spring.orm.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {

    List<Author> getAllAuthors();

    Optional<Author> getAuthorById(long id);

    Author save(Author author);

    void delete(Author author);
}
