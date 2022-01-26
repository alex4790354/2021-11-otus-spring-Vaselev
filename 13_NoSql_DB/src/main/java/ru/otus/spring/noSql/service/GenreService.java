package ru.otus.spring.noSql.service;


import ru.otus.spring.noSql.domain.Genre;


import java.util.List;

public interface GenreService {

    Genre getGenreByName(String name);

    List<Genre> getGenres();

    String create(String name);

    void update(String id, String name);

    void delete(String name);
}
