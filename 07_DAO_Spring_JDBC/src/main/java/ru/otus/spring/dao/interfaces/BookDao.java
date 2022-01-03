package ru.otus.spring.dao.interfaces;

import ru.otus.spring.customExceptions.DaoException;
import ru.otus.spring.domain.Book;

import java.util.List;

public interface BookDao {

    int getCount();

    List<Book> getAll();

    Book getById(int id);

    void deleteById(int id);

    void updateById(int id, String newName);

    void insert(Book book) throws DaoException;

}
