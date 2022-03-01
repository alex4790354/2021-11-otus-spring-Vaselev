package ru.otus.spring.mongoDb.services;

import ru.otus.spring.mongoDb.domain.Author;

import java.util.List;

public interface AuthorService {

    String create(String name);

    Author findByName(String name);
    List<Author> findAll();

    void update(String oldName, String newName);

    void deleteByName(String name);

}
