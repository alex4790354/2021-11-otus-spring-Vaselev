package ru.otus.spring.orm.services;

import ru.otus.spring.orm.domain.Book;
import ru.otus.spring.orm.domain.Review;

import java.util.List;

public interface BookService {

    Book getBookById(long id);

    List<Book> getAllBooks();

    Long getBooksCount();

    void deleteBook(long id);

    Book saveBook(Book book);

    Book saveBook(Long id, String newTitle);

    List<Review> getReviewsByBookId(Long bookId);

}
