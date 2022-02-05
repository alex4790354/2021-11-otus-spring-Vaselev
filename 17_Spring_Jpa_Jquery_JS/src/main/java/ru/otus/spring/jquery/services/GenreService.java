package ru.otus.spring.jquery.services;

import ru.otus.spring.jquery.domain.Genre;

import java.util.List;

public interface GenreService {

    Long count();

    List<Genre> findAll();

    Genre findById(Long id);

    Genre save(Genre genre);

    Genre save(String fullName);

    Genre save(long id, String title);

    void deleteById(Long id);
}
