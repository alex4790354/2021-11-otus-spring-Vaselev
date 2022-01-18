package ru.otus.spring.jpa.services;

import ru.otus.spring.jpa.domain.Author;

import java.util.List;

public interface AuthorService {

    long create(String name);

    Author getById(long id);

    List<Author> getAll();

    void update(long id, String fullName);

    void delete(long id);

}
