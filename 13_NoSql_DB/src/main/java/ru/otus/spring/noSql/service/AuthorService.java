package ru.otus.spring.noSql.service;

import ru.otus.spring.noSql.domain.Author;
import java.util.List;


public interface AuthorService {

    Author getByName(String name);

    List<Author> getAll();

    String create(String name);

    void update(String id, String name);

    void delete(String name);

}
