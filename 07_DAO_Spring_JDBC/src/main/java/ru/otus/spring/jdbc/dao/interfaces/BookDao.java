package ru.otus.spring.jdbc.dao.interfaces;

import ru.otus.spring.jdbc.customExceptions.DaoException;
import ru.otus.spring.jdbc.domain.Book;

import java.util.List;

public interface BookDao {

    int getCount();

    List<Book> getAll();

    Book getById(long id);

    void deleteById(long id);

    void updateNameById(long id, String newName);

    void updateById(Book book);

    void insert(Book book);

}
