package ru.otus.spring.mongoDb.services;

import ru.otus.spring.mongoDb.domain.Genre;

import java.util.List;

public interface GenreService {

    String create(String name);
    void update(String oldName, String newName);
    Genre findByName(String name);
    List<Genre> findAll();
    void deleteByName(String name);

}
