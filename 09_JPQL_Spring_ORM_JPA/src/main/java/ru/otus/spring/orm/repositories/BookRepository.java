package ru.otus.spring.orm.repositories;

import ru.otus.spring.orm.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    Optional<Book> getBookById(long id);

    List<Book> getAllBooks();

    // Для JPA: решение проблемы N+1:
    /*@EntityGraph(attributePaths = {"author", "genre"})
    @Override
    List<Book> findAll();*/


    Long getBooksCount();

    void deleteBook(Book book);

    Book saveBook(Book newBook);

}
