package ru.otus.spring.orm.repositories;

import ru.otus.spring.orm.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    Optional<Book> getBookById(long id);

    List<Book> getAllBooks();

    Long getBooksCount();

    void deleteBook(Book book);

    Book saveBook(Book newBook);

}
