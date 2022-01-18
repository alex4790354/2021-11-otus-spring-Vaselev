package ru.otus.spring.jpa.services;

import ru.otus.spring.jpa.domain.Genre;

import java.util.List;

public interface GenreService {

    long create(String fullName);

    List<Genre> getGenres();

    Genre getGenreById(long id);

    void update(long id, String title);

    void delete(long id);

}
