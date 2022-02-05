package ru.otus.spring.jquery.service;

import ru.otus.spring.jquery.domain.Genre;

import java.util.List;

public interface GenreService {

    Long count();

    List<Genre> findAll();

    Genre findById(Long id);

    Genre save(Genre genre);

    void deleteById(Long id);
}
