package ru.otus.spring.jquery.service;

import ru.otus.spring.jquery.domain.Author;

import java.util.List;

public interface AuthorService {

    Long count();

    List<Author> findAll();

    Author findById(Long id);

    Author save(Author author);

    void deleteById(Long id);
}
