package ru.otus.spring.noSql.services;

import ru.otus.spring.noSql.domain.Book;

import java.util.List;

public interface BookService {

    Book findById(long id);

    List<Book> findAll();

    Long getBooksCount();

    void deleteBook(long id);

    Book saveBook(Book book);

    Book saveBook(Long id, String newTitle);

    //List<Note> getNotesByBookId(Long bookId);

}
