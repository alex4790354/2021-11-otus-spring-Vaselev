package ru.otus.spring.jdbc.dao.interfaces;

import ru.otus.spring.jdbc.domain.Genre;

import java.util.List;

public interface GenreDao {

    List<Genre> getAll();

    Genre getById(long id);

}
