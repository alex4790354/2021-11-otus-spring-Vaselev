package ru.otus.spring.orm.services;

import ru.otus.spring.orm.domain.Book;
import ru.otus.spring.orm.domain.Review;

import java.util.List;

public interface BookService {

    Book getBookById(long id);

    List<Book> getAllBooks();

    List<Book> getBooksByName(String bookName);

    Long getBooksCount();

    void deleteBook(long id);

    Book saveBook(Book book);

    Book saveBook(Long id, String newTitle);

    int updateBookName(String oldBookName, String newBookName);

    List<Review> getReviewsByBookId(Long bookId);

}
