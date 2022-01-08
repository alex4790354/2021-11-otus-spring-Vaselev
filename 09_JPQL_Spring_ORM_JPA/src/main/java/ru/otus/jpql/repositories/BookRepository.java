package ru.otus.jpql.repositories;

import ru.otus.jpql.domain.Book;
import ru.otus.jpql.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    List<Book> findAll();

    Long getBooksCount();

    Optional<Book> findBookById(long id);

    List<String> findAllBookNames();

    List<Book> findBookByAuthorName(String authorName);

    List<Book> findBookByGenreName(String genreName);

    List<Book> findBookByName(String bookName);

    void updateBookName(String oldBookName, String newBookName);

    void deleteById(long id);

    void insertNewBook(Book newBook);

}
