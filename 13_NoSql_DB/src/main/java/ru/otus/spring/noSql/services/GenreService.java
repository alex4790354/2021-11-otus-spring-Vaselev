package ru.otus.spring.noSql.services;

import ru.otus.spring.noSql.domain.Genre;

import java.util.List;

public interface GenreService {

    long create(String fullName);

    List<Genre> findAll();

    Genre findById(long id);

    void save(long id, String title);

    void delete(long id);

}
