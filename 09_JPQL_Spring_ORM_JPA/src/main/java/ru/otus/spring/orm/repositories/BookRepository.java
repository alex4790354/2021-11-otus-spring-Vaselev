package ru.otus.spring.orm.repositories;

import ru.otus.spring.orm.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    List<Book> findAll();

    Long getBooksCount();

    Optional<Book> findBookById(long id);

    List<String> findAllBookNames();

    List<Book> findBooksByName(String bookName);

    List<Book> findBooksByAuthorName(String authorName);

    List<Book> findBooksByGenreName(String genreName);

    void updateBookName(String oldBookName, String newBookName);

    void updateBookById(Book newBook);

    void deleteById(long id);

    void insertNewBook(Book newBook);

}
