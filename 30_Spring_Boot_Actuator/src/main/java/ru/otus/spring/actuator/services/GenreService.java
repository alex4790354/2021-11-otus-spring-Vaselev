package ru.otus.spring.actuator.services;

import ru.otus.spring.actuator.domain.Genre;

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
