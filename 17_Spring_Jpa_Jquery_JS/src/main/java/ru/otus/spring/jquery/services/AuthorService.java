package ru.otus.spring.jquery.services;

import ru.otus.spring.jquery.domain.Author;

import java.util.List;

public interface AuthorService {

    Long count();

    List<Author> findAll();

    Author findById(Long id);

    Author save(Author author);

    Author save(String fullName);

    void save(long id, String fullName);

    void deleteById(Long id);
}
