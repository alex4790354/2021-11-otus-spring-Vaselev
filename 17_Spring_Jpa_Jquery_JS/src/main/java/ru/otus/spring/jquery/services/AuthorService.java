package ru.otus.spring.jquery.services;

import ru.otus.spring.jquery.domain.Author;

import java.util.List;

public interface AuthorService {

    Author findById(long id);

    List<Author> findAll();

    Author create(String name);

    void save(long id, String fullName);

    void delete(long id);

}
