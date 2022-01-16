package ru.otus.spring.orm.repositories;

import ru.otus.spring.orm.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    Optional<Book> getBookById(long id);

    List<Book> getAllBooks();

    List<Book> getBooksByStartName(String bookName);

    List<Book> getBooksByAuthorId(Long authorId);

    List<Book> getBooksByGenreId(Long genreId);

    Long getBooksCount();

    void deleteBook(Book book);

    Book saveBook(Book newBook);

    int updateBookName(String oldBookName, String newBookName);

}
