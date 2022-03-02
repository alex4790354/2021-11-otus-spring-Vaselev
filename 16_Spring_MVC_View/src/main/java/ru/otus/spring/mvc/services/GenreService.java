package ru.otus.spring.mvc.services;

import ru.otus.spring.mvc.domain.Genre;
import java.util.List;


public interface GenreService {

    long create(String fullName);

    List<Genre> findAll();

    Genre findById(long id);

    void save(long id, String title);

    void delete(long id);

}
