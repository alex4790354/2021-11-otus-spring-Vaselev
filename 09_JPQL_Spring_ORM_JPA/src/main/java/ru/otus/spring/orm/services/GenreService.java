package ru.otus.spring.orm.services;

import ru.otus.spring.orm.domain.Genre;

import java.util.List;

public interface GenreService {

    long create(String fullName);

    List<Genre> getGenres();

    Genre getGenreById(long id);

    void update(long id, String title);

    void delete(long id);

}
