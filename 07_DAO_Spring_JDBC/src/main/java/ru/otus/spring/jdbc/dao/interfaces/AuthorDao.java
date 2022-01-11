package ru.otus.spring.jdbc.dao.interfaces;

import ru.otus.spring.jdbc.domain.Author;

import java.util.List;

public interface AuthorDao {

    List<Author> getAll();

    Author getById(long id);

}
