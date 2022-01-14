package ru.otus.spring.orm.repositories;

import ru.otus.spring.orm.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    Optional<Book> findBookById(long id);

    ///////////// CHECKED  ^^

    List<Book> findAll();

    Long getBooksCount();

    List<String> findAllBookNames();

    List<Book> findBooksByName(String bookName);

    List<Book> findBooksByAuthorName(String authorName);

    List<Book> findBooksByGenreName(String genreName);

    int updateBookName(String oldBookName, String newBookName);

    Book updateBook(Book newBook);

    int deleteById(long id);

    void insertNewBook(Book newBook);

}
