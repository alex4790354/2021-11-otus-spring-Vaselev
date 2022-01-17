package ru.otus.spring.orm.repositories;

import ru.otus.spring.orm.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository {

    Optional<Genre> getGenreById(long id);

    List<Genre> getAllGenres();

    Genre save(Genre genre);

    void delete(Genre genre);

}
