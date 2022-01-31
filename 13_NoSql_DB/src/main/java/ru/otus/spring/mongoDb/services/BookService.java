package ru.otus.spring.mongoDb.services;

import ru.otus.spring.mongoDb.domain.Book;
import ru.otus.spring.mongoDb.domain.Note;

import java.util.List;

public interface BookService {

    String create(String name, String authorName, String genreName);

    void update(String oldName, String newName);

    void deleteByName(String name);

    Book findByName(String name);
    List<Book> findAll();
    List<Book> findByAuthor(String name);
    List<Book> findByGenre(String name);

}
